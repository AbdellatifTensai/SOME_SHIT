class OutputImage{

    private static final int WIDTH = 256;
    private static final int HEIGHT = 256;
    Vec3 color;

    public static void main(String[] args) {
        new OutputImage().output_ppm2();
    }

    private void output_ppm2(){
        System.out.print("P3\n"+WIDTH+" "+HEIGHT+"\n255\n");
        for(int x=HEIGHT-1; x>=0; --x){
            for(int y=0; y<WIDTH; ++y){
                color = new Vec3((double) y/(WIDTH-1), (double) x/(HEIGHT-1),.25D);
                color.printColor();
            }
        }
    }

    private void output_ppm1(){
        System.out.print("P3\n"+WIDTH+" "+HEIGHT+"\n255\n");
        for(int x=HEIGHT-1; x>=0; --x){
            for(int y=0; y<WIDTH; ++y){
                double r = (double) y/ (WIDTH-1);
                double g = (double) x/ (HEIGHT-1);
                double b = .25D;

                int ir = (int) (255.999D *r);
                int ig = (int) (255.999D *g);
                int ib = (int) (255.999D *b);
                System.out.print(ir+" "+ig+" "+ib+"\n");
            }
        }
    }
}