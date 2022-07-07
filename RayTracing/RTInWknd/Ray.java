package com.abdo.rtinwkn;

class Ray {
    
    Vec3 origin;
    Vec3 direction;

    Ray(Vec3 origin, Vec3 direction){
        this.origin = origin;
        this.direction = direction;
    }
    Ray(){
        this.origin = new Vec3();
        this.direction = new Vec3();
    }
    
    Vec3 at(double t){
        return new Vec3(
            origin.x() + direction.x()*t,
            origin.y() + direction.y()*t,
            origin.z() + direction.z()*t);
    }

    Vec3 origin(){ return this.origin;}
    Vec3 direction(){ return this.direction;}

    @Override
    public String toString() {
        return "Origin: "+origin+" Direction: "+direction;
    }
}
