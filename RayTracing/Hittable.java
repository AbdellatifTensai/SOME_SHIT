abstract class Hittable {

    double t_at_hit;
    Vec3 point_at_hit;
    Vec3 normal_at_hit;
    boolean outside_face;

    boolean hit(Ray ray, double t_min, double t_max){
        return false;
    }

}
