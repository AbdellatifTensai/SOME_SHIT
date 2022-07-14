import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.SplittableRandom;
import java.util.stream.IntStream;

class Scene{
    
    private static final double ASPECT_RATIO = 3.0D/2.0D;
    private static final int WIDTH = 1200;
    private static final int HEIGHT =(int) (WIDTH/ASPECT_RATIO);

    private static final int SAMPLES_PER_PIXEL = 16;
    private static final int BOUNCES = 8;

    private static final Vec3 ORIGIN = new Vec3(13.0D, 2.0D, 3.0D);
    private static final Vec3 TARGET = new Vec3(0.0D, 0.0D, .0D);
    private static final Vec3 VUP = new Vec3(0.0D, 1.0D, 0.0D);
    private static final double FOCUS_DIST = 10.0D;
    private static final double APERATURE = 0.1D;
    private static final double FOV = 20.0D;

    private static final Hittable[] OBJECTS = randomSpheres();

    public static void main(String[] args) throws IOException{
        long t1 = System.currentTimeMillis();
        Scene scene = new Scene();
        Camera camera = new Camera(ASPECT_RATIO, FOV, ORIGIN, TARGET, VUP, APERATURE, FOCUS_DIST);
        String output = scene.renderImage(camera);

        Path image = Paths.get("C:/Users/omen/Desktop/SOME_SHIT/RayTracing/image3.ppm");
        Files.deleteIfExists(image);
        Files.write(image,output.getBytes(),StandardOpenOption.CREATE);

        System.out.println("Time: "+ ((System.currentTimeMillis() - t1)/1000.0D) + "s");
    }

    private String renderImage(Camera camera) throws IOException{
        StringBuilder sb = new StringBuilder();
        SplittableRandom rand = new SplittableRandom();
        Vec3 pixel_color = new Vec3(0.0D);
        sb.append("P3\n"+ WIDTH+ " "+ HEIGHT+ "\n255\n");

        IntStream.range(1-HEIGHT,1).forEach(y->{
            System.out.println(-y);
            IntStream.range(0,WIDTH).forEach(x->{
                pixel_color.set(0.0D, 0.0D, 0.0D); 
                IntStream.range(0,SAMPLES_PER_PIXEL).parallel().forEach(s->{
                    double u = (double) ((((double) x) + rand.nextDouble()) / (WIDTH-1));
                    double v = (double) ((((double) -y) + rand.nextDouble()) / (HEIGHT-1));
                    Ray camera_ray = camera.cameraRay(u, v);
                    pixel_color.addToThis(rayColor(camera_ray, BOUNCES));
                });
                sb.append(Vec3ToRGB(pixel_color));
            });
        });

        // for(int y=HEIGHT-1; y>=0; --y){
        //     System.out.println(y);
        //     for(int x=0; x<WIDTH; ++x){
        //         pixel_color.set(0.0D, 0.0D, 0.0D);
        //         for(int s=0; s<SAMPLES_PER_PIXEL; ++s){
        //             double u = (double) ((((double) x) + rand.nextDouble()) / (WIDTH-1));
        //             double v = (double) ((((double) y) + rand.nextDouble()) / (HEIGHT-1));
        //             Ray camera_ray = camera.cameraRay(u, v);
        //             pixel_color.addToThis(rayColor(camera_ray, BOUNCES));
        //         }
        //         sb.append(Vec3ToRGB(pixel_color));
        //     }
        // }
        return sb.toString();
    }

    private Vec3 rayColor(Ray ray, int bounces){
        if(bounces<= 0) return new Vec3();

        Hittable hit_object = hitObject(ray, 0.001D, Double.POSITIVE_INFINITY);

        if(hit_object == null){
            // lerp between light-blue and white
            Vec3 unit_direction = ray.direction().unitVecotr();
            double t = .5D * (unit_direction.y() + 1.0D);
            return new Vec3(1.0D).scale(1.0D - t).add(new Vec3(.5D, .7D, 1.0D).scale(t));
        }

        Ray scattered_ray = hit_object.material.scatter(ray, hit_object);

        if(scattered_ray == null){
            return new Vec3();
        }

        return rayColor(scattered_ray, bounces-1).multiply(hit_object.albedo);
    }

    private Hittable hitObject(Ray ray, double t_min, double t_max){
        Hittable object = null;
        double t_closest = t_max;

        int size = OBJECTS.length; 
        for(int x=0; x<size; x++){
            if(OBJECTS[x].hit(ray, t_min, t_closest)){
                object = OBJECTS[x];
                t_closest = object.t_at_hit;
            }
        }
        return object;
    }

    private String Vec3ToRGB(Vec3 color){
        double scale = 1.0D / (double) SAMPLES_PER_PIXEL;
        Vec3 temp = color.scale(scale).squreRoot();
        return (int) (256.0D *clamp(temp.x(), 0.0D, 0.999D)) +" "+
               (int) (256.0D *clamp(temp.y(), 0.0D, 0.999D)) +" "+
               (int) (256.0D *clamp(temp.z(), 0.0D, 0.999D)) +"\n";
    }

    private double clamp(double x, double min, double max){
        if(x<min) return min;
        if(x>max) return max;
        return x;
    }

    private static Hittable[] randomSpheres(){
        ArrayList<Hittable> spheres = new ArrayList<>();
        SplittableRandom rand = new SplittableRandom();
        System.out.println("***** Picking Spheres *****");
        for(int x=-11; x<11; x++){
            for(int y=-11; y<11; y++){

                double rnd = rand.nextDouble();
                Vec3 center = new Vec3(x+.9D*rand.nextDouble(), .02D, y+.9D*rand.nextDouble());

                if(center.sub(new Vec3(4.0D, .2D, 0.0D)).lenght() > .9D){
                    if(rnd < .8D){
                        Vec3 color = Vec3.random(.0D, 1.0D).multiply(Vec3.random(.0D, 1.0D));
                        spheres.add(new Sphere().center(center).color(color).material(Reflection.LAMBERTIAN).radius(.2D));
                    }
                    else if(rnd <.95D){
                        Vec3 color = Vec3.random(.5D, 1.0D);
                        double fuzz = .5D*rand.nextDouble();
                        spheres.add(new Sphere().center(center).color(color).material(Reflection.METAL).radius(.2D).fuzz(fuzz));
                    }
                    else{
                        spheres.add(new Sphere().center(center).material(Reflection.DIELECTRIC).radius(.2D).refraction(1.5D));
                    }
                }
            }
        }
        spheres.add(new Sphere().center(new Vec3(.0D,1.0D,.0D)).material(Reflection.DIELECTRIC).radius(1.0D).refraction(1.5D));
        spheres.add(new Sphere().center(new Vec3(-4.0D,1.0D,.0D)).material(Reflection.LAMBERTIAN).radius(1.0D).color(new Vec3(.4D,.2D,.1D)));
        spheres.add(new Sphere().center(new Vec3(4.0D,1.0D,.0D)).material(Reflection.METAL).radius(1.0D).fuzz(.0D).color(new Vec3(.7D,.6D,.5D)));
        spheres.add(new Sphere().center(new Vec3(.0D, -1000.0D, .0D)).radius(1000.0D).material(Reflection.LAMBERTIAN).color(new Vec3(.5D, .5D, .5D)));

        System.out.println("*****Finished Picking Spheres*****");

        return spheres.toArray(new Hittable[]{});
    }
}
