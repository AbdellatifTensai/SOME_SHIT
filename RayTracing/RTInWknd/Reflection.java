import java.util.SplittableRandom;

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

    public static final Reflection DIELECTRIC = (ray, hit_object) -> {
        hit_object.albedo = new Vec3(1.0D);
        double refration_ratio = hit_object.outside_face? 1.0D/hit_object.index_refraction: hit_object.index_refraction;

        Vec3 unit_direction = ray.direction().unitVecotr();

        double cos_theta = Math.min(unit_direction.inverse().dot(hit_object.normal_at_hit), 1.0D);
        double sin_theta = Math.sqrt(1.0D - cos_theta*cos_theta);

        if(refration_ratio * sin_theta>1 || reflectance(cos_theta,refration_ratio) > new SplittableRandom().nextDouble()){
            Vec3 refracted_direction = unit_direction.reflect(hit_object.normal_at_hit);
            return new Ray(hit_object.point_at_hit, refracted_direction);
        }
        else{
            Vec3 refracted_direction = unit_direction.refract(hit_object.normal_at_hit, refration_ratio);
            return new Ray(hit_object.point_at_hit, refracted_direction);
        }
    };

    static double reflectance(double cos, double ref_idx){
        double r0 = (1.0D - ref_idx) / (1.0D + ref_idx);
        r0 = r0*r0;
        return r0 + (1-r0) * Math.pow(1-cos, 5);
    }
    
}