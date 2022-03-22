public class Planet {
    //定义Planet类

    public double xxPos, yyPos;//定义行星的位置x,y
    public double xxVel, yyVel;//定义行星速度x,y方向
    public double mass;        //定义地球质量
    public String imgFileName; //与描述行星的图像对应的文件名
    //成员变量

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        //构造函数xP,yP等为局部变量
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Planet(Planet p) {
        //重载构造函数
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        //
    }

    //double与返回值相关
    public double calcDistance(Planet p) {
        //距离
        double r;
        r = Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
        return r;

    }

    public double calcForceExertedBy(Planet p) {
        //力总
        double f;
        double r;
        r = this.calcDistance(p);
        f = ((this.mass) * (p.mass) * 6.67 * 1e-11) / (r * r);
        return f;

    }

    public double calcForceExertedByX(Planet p) {
        //力x方向
        double fx;
        double f;
        double r;
        r = this.calcDistance(p);
        f = this.calcForceExertedBy(p);
        fx = f * (p.xxPos - this.xxPos) / r;
        return fx;
    }

    public double calcForceExertedByY(Planet p) {
        //力y方向
        double f;
        double fy;
        double r;
        r = this.calcDistance(p);
        f = this.calcForceExertedBy(p);
        fy = f * (p.yyPos - this.yyPos) / r;
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double fxall = 0.0;
        for (int i = 0; i < p.length; i++) {

            //等于自身时跳过
            if (this.equals(p[i])) {
                continue;
            }
            fxall = fxall + this.calcForceExertedByX(p[i]);
        }
        return fxall;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double fyall = 0.0;
        for (int i = 0; i < p.length; i++) {
            if (this.equals(p[i])) {
                continue;
            }
            fyall = fyall + this.calcForceExertedByY(p[i]);
        }
        return fyall;
    }
    //不需要返回值，所以用void
    void update(double dt,double fX,double fY){
        double ax,ay,Vx,Vy,Posx,Posy;
        ax=fX/this.mass;
        ay=fY/this.mass;
        Vx=this.xxVel+ax*dt;
        Vy=this.yyVel+ay*dt;
        Posx=Vx*dt+this.xxPos;
        Posy=Vy*dt+this.yyPos;
        this.xxPos=Posx;
        this.yyPos=Posy;
        this.xxVel=Vx;
        this.yyVel=Vy;

    }
    //draw不返回 不接收
    void draw(){
        String ImgAddre="./images/"+this.imgFileName;
        //地址
        StdDraw.picture(this.xxPos,this.yyPos,ImgAddre);

    }

}