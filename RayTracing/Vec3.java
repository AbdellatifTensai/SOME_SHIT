class Vec3{

    double[] data;
    
    Vec3(){ data = new double[]{0,0,0}; }

    Vec3(double x, double y, double z){ data = new double[]{x,y,z}; }

    Vec3(Vec3 v){ data = new double[]{v.x(), v.y(), v.z()}; }

    Vec3(double i){ data = new double[]{i,i,i}; }

    double x(){ return data[0];}
    double y(){ return data[1];}
    double z(){ return data[2];}

    Vec3 scale(double t){
        return new Vec3(x() * t, y() * t, z() * t);
    }

    Vec3 inverse(){
        return new Vec3(-x(), -y(), -z());
    }

    Vec3 add(Vec3 v){
        return new Vec3(x() + v.x(), y() + v.y(), z() + v.z());
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
        return new Vec3((max - min)*Math.random() + min,
            (max - min)*Math.random() + min,
            (max - min)*Math.random() + min);
    }

    static Vec3 randomInUnitSphere(){
        while(true){
            Vec3 v = random(-1.0D, 1.0D);
            if(v.lenghtSquared()>= 1) continue;
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

    @Override
    public String toString() {
        return "x: "+ x() + " y: " + y() + " z: " + z();
    }
}