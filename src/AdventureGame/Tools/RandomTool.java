package AdventureGame.Tools;

import java.util.Random;

public class RandomTool {
    private static Random random = new Random();

    public static double getRand(double min, double max){
        return min + (max - min)*random.nextDouble();
    }

    public static int getRandInt(int min, int max){
        return random.nextInt(max - min) + min;
    }

    public static boolean half(){
        double t = random.nextDouble();
        return t >= 0.5;
    }
}
