package com.abdo.rtinwkn;

class Camera {

    private double aspect_ratio;      
    private Vec3 origin;

    private Vec3 HORIZONTAL;
    private Vec3 VERTICAL;
    private Vec3 LOWER_LEFT_CORNER;
    private Vec3 u,v,w;
    private double lens_radius;

    Camera(double ar, double theta, Vec3 origin, Vec3 look_at, Vec3 vup, double aperature, double focus_dist){
        this.aspect_ratio = ar;
        this.lens_radius = aperature/2.0D;

        double H = Math.tan(theta*Math.PI/180/2.0D);
        double VIEW_PORT_HEIGHT = 2.0D * H;
        double  VIEW_PORT_WIDTH = aspect_ratio * VIEW_PORT_HEIGHT;

        this.w = origin.sub(look_at).unitVecotr();
        this.u = vup.cross(w).unitVecotr();
        this.v = w.cross(u);
        this.origin = origin;

        HORIZONTAL = u.scale(VIEW_PORT_WIDTH* focus_dist);
        VERTICAL = v.scale(VIEW_PORT_HEIGHT* focus_dist);
        LOWER_LEFT_CORNER = origin.sub(HORIZONTAL.scale(.5D)).sub(VERTICAL.scale(.5D)).sub(w.scale(focus_dist));
    }   

    Ray cameraRay(double s, double t){
        Vec3 rd = Vec3.randomInUnitDisk().scale(lens_radius);
        Vec3 offset = u.scale(rd.x()).add(v.scale(rd.y()));

        return new Ray(origin.add(offset),LOWER_LEFT_CORNER
            .add(HORIZONTAL.scale(s))
            .add(VERTICAL.scale(t))
            .sub(origin)
            .sub(offset));
    }

}
