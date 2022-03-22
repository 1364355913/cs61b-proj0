public class NBody {
   public static double readRadius(String s) {
        double r;
        In in = new In(s);
        int firstData = in.readInt();
        double secondData = in.readDouble();
       //r = in.readDouble();
        r=secondData;
        return r;
    }

    static Planet[] readPlanets(String filename) {
        double xxPos, yyPos, xxVel, yyVel, mass;
        String imgFilename;
        In in = new In(filename);
        int firstData = in.readInt();
        double secondData = in.readDouble();
        Planet[] ns = new Planet[firstData];
        for (int i = 0; i < firstData; i++) {
            //0~4
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            imgFilename = in.readString();
            ns[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFilename);

        }
        return ns;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]); //命令行double数据
        double dt = Double.parseDouble(args[1]);//命令行double数据
        String filename = args[2];              //命令行中文件路径

        double time=0;//定义时间
        double R;     //宇宙半径

        In in = new In(filename);
        int firstItemInFile = in.readInt();//读取数据中第一行行星数量
        double secondItemInFile = in.readDouble();

        double[] xForces = new double[firstItemInFile];
        double[] yForces = new double[firstItemInFile];
        //Planet[] ns = new Planet[firstItemInFile];
        R =readRadius(filename);//宇宙半径读取文件中数据

        Planet[] p=NBody.readPlanets(filename);//定义数组p 数据来源于文件filename，也就是命令行第三个字符串

        StdDraw.enableDoubleBuffering();//双重缓冲
         StdDraw.show();
         StdDraw.setScale(-R, R);//设置宇宙半径

         StdDraw.picture(0, 0, "./images/starfield.jpg");//绘制宇宙background
        //绘制所有行星
        /*for(int i=0;i<5;i++){
            p[i].draw();
        }*/

        //创建动画
        while(time<T){
            for(int i=0;i<firstItemInFile;i++){
                xForces[i]=p[i].calcNetForceExertedByX(p);
                yForces[i]=p[i].calcNetForceExertedByY(p);
                p[i].update(dt,xForces[i],yForces[i]);

            }


        for(int i=0;i<firstItemInFile;i++){

            p[i].draw();



        }

            //
            StdDraw.show();
            StdDraw.pause(10);
            time=time+dt;
            StdDraw.clear();
            StdDraw.picture(0, 0,"./images/starfield.jpg");
        }

    }
}
