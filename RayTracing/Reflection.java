@FunctionalInterface
interface Reflection{

    Ray scatter(Ray ray, Hittable hit_object);

    public static final Reflection METAL = (ray, hit_object) -> {
            Vec3 reflected_direction = ray.direction().unitVecotr().reflect(hit_object.normal_at_hit);
            Ray scatterd = new Ray(hit_object.point_at_hit, reflected_direction.add(Vec3.randomInUnitSphere().scale(hit_object.fuzz)));

            return scatterd.direction().dot(hit_object.normal_at_hit) > 0.0D? scatterd: null;
    };
    

    public static final Reflection LAMBERTIAN = (ray, hit_object) ->{
            Vec3 scattered_direction = hit_object.normal_at_hit.add(Vec3.randomInUnitSphere());

            if(scattered_direction.nearZero()){ 
                scattered_direction = hit_object.normal_at_hit;
            }

            return new Ray(hit_object.point_at_hit, scattered_direction);

    }; 
    
}