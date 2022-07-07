package com.abdo.rtinwkn;

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
        double a = ray.direction().lenghtSquared();
        double half_b = ray.direction().dot(oc);
        double c = oc.lenghtSquared() - radius*radius;
        double discriminant = half_b*half_b - a*c;
        if(discriminant < 0) return false; 
        
        double root = (-half_b - Math.sqrt(discriminant)) / a;
        if(root<t_min || root>t_max){
            root = (-half_b + Math.sqrt(discriminant)) / a;
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
