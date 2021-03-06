import java.util.SplittableRandom;

class Vec3{

    double[] data;
    
    Vec3(){ data = new double[]{0,0,0}; }

    Vec3(double x, double y, double z){ data = new double[]{x,y,z}; }

    Vec3(Vec3 v){ data = new double[]{v.x(), v.y(), v.z()}; }

    Vec3(double i){ data = new double[]{i,i,i}; }

    double x(){ return data[0];}
    double y(){ return data[1];}
    double z(){ return data[2];}

    void set(double x, double y, double z){
        this.data[0] = x;
        this.data[1] = y;
        this.data[2] = z;
    }

    Vec3 scale(double t){
        return new Vec3(x() * t, y() * t, z() * t);
    }

    Vec3 inverse(){
        return new Vec3(-x(), -y(), -z());
    }

    Vec3 add(Vec3 v){
        return new Vec3(x() + v.x(), y() + v.y(), z() + v.z());
    }

    void addToThis(Vec3 v){
        this.data[0] = v.x();
        this.data[1] = v.y();
        this.data[2] = v.z();
    }

    Vec3 sub(Vec3 v){
        return new Vec3(x() - v.x(), y() - v.y(), z() - v.z());
    }

    double dot(Vec3 v){
        return x()*v.x() + y()*v.y() + z()*v.z();
    }

    Vec3 multiply(Vec3 v){
        return new Vec3(x()*v.x(), y()*v.y(), z()*v.z());
    }

    Vec3 pow(double t){
        return new Vec3(Math.pow(x(), t),
            Math.pow(y(), t),
            Math.pow(z(), t));
    }
    
    double lenghtSquared(){
        Vec3 v = new Vec3(x(), y(), z());
        return v.dot(v);
    }

    double lenght(){
        return Math.sqrt(lenghtSquared());
    }

    Vec3 unitVecotr(){
        return new Vec3(x()*1.0D/lenght(),y()*1.0D/lenght(),z()*1.0D/lenght());
    }
    
    Vec3 cross(Vec3 v){
        return new Vec3(y()*v.z() - z()*v.y(),
                        z()*v.x() - x()*v.z(),
                        x()*v.y() - y()*v.x());
    }

    Vec3 squreRoot(){
        return new Vec3(Math.sqrt(x()), Math.sqrt(y()), Math.sqrt(z()));
    }

    static Vec3 random(double min, double max){
        SplittableRandom rand = new SplittableRandom();
        return new Vec3((max - min)*rand.nextDouble() + min,
            (max - min)*rand.nextDouble() + min,
            (max - min)*rand.nextDouble() + min);
    }

    static Vec3 randomInUnitSphere(){
        while(true){
            Vec3 v = random(-1.0D, 1.0D);
            if(v.lenghtSquared()>= 1) continue;
            return v;
        }
    }

    static Vec3 randomInUnitDisk(){
        SplittableRandom rand = new SplittableRandom();
        while(true){
            Vec3 v = new Vec3(2.0D*rand.nextDouble() -1.0D, 2.0D*rand.nextDouble() -1.0D, 0.0D);
            if(v.lenghtSquared() >= 1.0D) continue;
            return v;
        }
    }

    boolean nearZero(){
        final double zero_plus = 1.0e-8;
        return (x()<zero_plus && y()< zero_plus && z()< zero_plus);
    }

    Vec3 reflect(Vec3 n){
        Vec3 v = new Vec3(x(), y(), z());
        return v.sub(n.scale(v.dot(n)).scale(2.0D));
    }

    Vec3 refract(Vec3 n, double etai_over_etat) {
        Vec3 r = new Vec3(x(), y(), z());
        double cos_theta = Math.min(r.inverse().dot(n), 1.0D);
        Vec3 r_out_perp = n.scale(cos_theta).add(r).scale(etai_over_etat);
        Vec3 r_out_parallel = n.scale(-Math.sqrt(1-r_out_perp.lenghtSquared()));
        return r_out_perp.add(r_out_parallel);
    }

    @Override
    public String toString() {
        return "x: "+ x() + " y: " + y() + " z: " + z();
    }
}