class Sphere extends Hittable {
    
    Vec3 center;
    double radius;
    
    Sphere(Vec3 center, Vec3 color, double radius, Reflection material, double fuzz){
        this.center = center;
        this.albedo = color;
        this.radius = radius;
        this.material = material;
        this.fuzz = fuzz;
    }

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
