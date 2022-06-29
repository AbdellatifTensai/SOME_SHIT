class Color extends Vec3{

    Color(double x, double y, double z){super(x, y, z);}

    Color(){super();}

    Color(Vec3 color){super(color.x(), color.y(), color.z());}

    void printColor(){
        System.out.print(
            (int) (255.999D *x()) +" "+
            (int) (255.999D *y()) +" "+
            (int) (255.999D *z()) +"\n"
        );
    }

}