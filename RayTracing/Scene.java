import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class Scene{
    
    private static final double ASPECT_RATIO = 16.0D/9.0D;
    private static final int WIDTH = 400;
    private static final int HEIGHT =(int) (WIDTH/ASPECT_RATIO); 
    private static final int SAMPLES_PER_PIXEL = 32;
    private static final int BOUNCES = 10;

    private static final Hittable[] OBJECTS = new Hittable[]{
        new Sphere(new Vec3(0.0D, -100.5D, -1.0D), new Vec3(.8D, .8D, 0.0D),  100.0D, Reflection.LAMBERTIAN,1.0D),
        new Sphere(new Vec3(0.0D, 0.0D, -1.0D), new Vec3(.7D, .3D, .3D), .5D, Reflection.LAMBERTIAN, 1.0D),
        new Sphere(new Vec3(-1.0D, 0.0D, -1.0D),   new Vec3(.8D),                   .5D, Reflection.METAL, 1.0D),
        new Sphere(new Vec3(1.0D, 0.0D, -1.0D), new Vec3(.8D, .6D, .2D) ,    .5D, Reflection.METAL, .3D)
    };

    public static void main(String[] args) throws IOException{
        Scene scene = new Scene();
        Camera camera = Camera.getInstance();
        String output = scene.renderImage(camera);

        Path image = Paths.get("C:/Users/omen/Desktop/SOME_SHIT/RayTracing/image3.ppm");
        Files.deleteIfExists(image);
        Files.write(image,output.getBytes(),StandardOpenOption.CREATE);
    }

    private String renderImage(Camera camera) throws IOException{
        StringBuilder sb = new StringBuilder();
        sb.append("P3\n"+ WIDTH+ " "+ HEIGHT+ "\n255\n");

        for(int y=HEIGHT-1; y>=0; --y){
            for(int x=0; x<WIDTH; ++x){
                Vec3 pixel_color = new Vec3(0.0D);
                for(int s=0; s<SAMPLES_PER_PIXEL; ++s){
                    double u = (double) ((((double) x) + Math.random()) / (WIDTH-1));
                    double v = (double) ((((double) y) + Math.random()) / (HEIGHT-1));
                    Ray camera_ray = camera.cameraRay(u, v);
                    pixel_color = pixel_color.add(rayColor(camera_ray, BOUNCES));
                }
                sb.append(Vec3ToRGB(pixel_color));
            }
        }
        return sb.toString();
    }

    private Vec3 rayColor(Ray ray, int bounces){
        if(bounces<= 0) return new Vec3();

        Hittable hit_object = hitObject(ray, 0.001D, Double.POSITIVE_INFINITY);
        if(hit_object == null){
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

        for(int x=0; x<OBJECTS.length; x++){
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

    private Vec3 randomInHemisphere(Vec3 normal){
        Vec3 in_unit_sphere = Vec3.randomInUnitSphere();
        return in_unit_sphere.dot(normal)>0? in_unit_sphere: in_unit_sphere.inverse();
    }
}
