class Camera {

    private static final Camera camera = new Camera();

    private static final double ASPECT_RATIO = 16.0D/9.0D;
    private static final double VIEW_PORT_HEIGHT = 2.0D;
    private static final double  VIEW_PORT_WIDTH = ASPECT_RATIO * VIEW_PORT_HEIGHT;
    private static final double FOCAL_LENGHT = 1.0;

    private static final Vec3 ORIGIN = new Vec3();
    private static final Vec3 HORIZONTAL = new Vec3(VIEW_PORT_WIDTH, 0.0D, 0.0D);
    private static final Vec3 VERTICAL = new Vec3(0.0D, VIEW_PORT_HEIGHT, 0.0D);
    private static final Vec3 LOWER_LEFT_CORNER = ORIGIN.sub(HORIZONTAL.scale(1.0D/2.0D)).sub(VERTICAL.scale(1.0D/2.0D)).sub(new Vec3(0.0D, 0.0D, FOCAL_LENGHT));

    private Camera(){}

    public static Camera getInstance(){
        return camera;
    }

    Ray cameraRay(double u, double v){
        return new Ray(ORIGIN,LOWER_LEFT_CORNER
            .add(HORIZONTAL.scale(u))
            .add(VERTICAL.scale(v))
            .sub(ORIGIN));
    }

}
