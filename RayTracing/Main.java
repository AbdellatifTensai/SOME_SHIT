class Main {
    
    private static final double ASPECT_RATIO = 16.0D/9.0D;
    private static final int WIDTH = 400;
    private static final int HEIGHT =(int) (WIDTH/ASPECT_RATIO); 
    private static final Hittable[] objects = new Hittable[]{
        new Sphere(new Vec3(0.0D, 0.0D, -1.0D), .5D),
        new Sphere(new Vec3(0.0D, -100.5D, -1.0D), 100.0D)
    };

    public static void main(String[] args) {
        double view_port_height = 2.0D;
        double view_port_width = ASPECT_RATIO * view_port_height;
        double focal_lenght = 1.0;

        Vec3 origin = new Vec3();
        Vec3 horizontal = new Vec3(view_port_width, 0.0D, 0.0D);
        Vec3 vertical = new Vec3(0.0D, view_port_height, 0.0D);
        Vec3 lower_left_corner = origin
            .sub(horizontal.scale(1.0D/2.0D))
            .sub(vertical.scale(1.0D/2.0D))
            .sub(new Vec3(0.0D, 0.0D, focal_lenght));

        System.out.print("P3\n"+ WIDTH+ " "+ HEIGHT+ "\n255\n");
        for(int y=HEIGHT-1; y>=0; --y){
            for(int x=0; x<WIDTH; ++x){
                double u = (double) ((double)x/(WIDTH-1));
                double v = (double) ((double)y/(HEIGHT-1));
                Vec3 direction = lower_left_corner
                    .add(horizontal.scale(u))
                    .add(vertical.scale(v))
                    .sub(origin);
                Ray ray = new Ray(origin, direction);
                Color pixel_color = rayColor(ray);
                pixel_color.printColor();
            }
        }
    }

    private static Color rayColor(Ray ray){
        Hittable hit_object = hitObject(ray, 0.0D, Double.POSITIVE_INFINITY);

        if(hit_object != null){
            Vec3 N = hit_object.normal_at_hit.add(new Vec3(1.0D)).scale(.5D);
            return new Color(N);
        }
        //lerp between light-blue and white
        Vec3 unit_direction = ray.direction().unitVecotr();
        double t = .5D * (unit_direction.y() + 1.0D);
        Vec3 color = new Vec3(1.0D)
            .scale(1.0D - t)
            .add(new Vec3(.5D, .7D, 1.0D).scale(t));

        return new Color(color); 
    }

    private static Hittable hitObject(Ray ray, double t_min, double t_max){
        Hittable object = null;
        double t_closest = t_max;
        for(int x=0; x<objects.length; x++){
            if(objects[x].hit(ray, t_min, t_closest)){
                object = objects[x];
                t_closest = object.t_at_hit;
            }
        }
        return object;
    }

}
