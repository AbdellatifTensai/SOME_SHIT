class Sphere extends Hittable {
    
    Vec3 center;
    double radius;
    
    Sphere(){
        this.center = new Vec3();
        this.albedo = new Vec3(1.0D);
        this.radius = 0.0D;
        this.material = null;
        this.fuzz = 0.0D;
        this.index_refraction = 0.0D;
    }

    Sphere center(Vec3 center)           { this.center = center;               return this; }
    Sphere radius(double radius)         { this.radius = radius;               return this; }
    Sphere color(Vec3 color)             { this.albedo = color;                return this; }
    Sphere material(Reflection material) { this.material = material;           return this; }
    Sphere fuzz(double fuzz)             { this.fuzz = fuzz;                   return this; }
    Sphere refraction(double refraction) { this.index_refraction = refraction; return this; }


    @Override
    boolean hit(Ray ray, double t_min, double t_max){
        Vec3 oc = ray.origin().sub(center);
        double a = ray.direction().dot(ray.direction);
        double b = 2.0D * ray.direction().dot(oc);
        double c = oc.dot(oc) - radius*radius;
        double discriminant = b*b - 4*a*c;
        if(discriminant < 0) return false; 
        
        double root = (-b - Math.sqrt(discriminant)) / (2.0D*a);
        if(root<t_min || root>t_max){
            root = (-b + Math.sqrt(discriminant)) / (2.0D*a);
            if(root<t_min || root>t_max){
                return false;
            }
        }

        t_at_hit = root;
        point_at_hit = ray.at(t_at_hit);
        setOutsideFace(ray, point_at_hit.sub(center).scale(1/radius));

        return true;
    }

    private void setOutsideFace(Ray ray, Vec3 normal){
        outside_face = ray.direction().dot(normal) < 0.0D; 
        normal_at_hit = outside_face? normal: normal.inverse();
    }

}
