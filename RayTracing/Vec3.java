class Vec3{

    double[] data;
    
    Vec3(){ data = new double[]{0,0,0}; }

    Vec3(double x, double y, double z){ data = new double[]{x,y,z}; }

    Vec3(Vec3 v){
        data = new double[]{v.x(), v.y(), v.z()};
    }

    Vec3(double i){
        data = new double[]{i,i,i};
    }

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

    double lenght(){
        Vec3 v = new Vec3(x(),y(),z());
        return Math.sqrt(v.dot(v));
    }

    Vec3 unitVecotr(){
        return new Vec3(x()*1.0D/lenght(),y()*1.0D/lenght(),z()*1.0D/lenght());
    }
    
    Vec3 cross(Vec3 v){
        return new Vec3(y()*v.z() - z()*v.y(),
                        z()*v.x() - x()*v.z(),
                        x()*v.y() - y()*v.x());
    }

    @Override
    public String toString() {
        return "x: "+ x() + " y: " + y() + " z: " + z();
    }
}