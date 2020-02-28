public class PowerMethod {
    private double matrA[][];
    private double curLambda = 1;
    private double prevLambda = 0;
    private double curX[];
    private double prevX[];
    private int n;
    private final double epsilon = 0.00001;
    private int count = 0;
    private double polinom[] = {1, -3.1966884499998454, 3.796847573496825, -2.0678062361747767, 0.5082483413018405, -0.044096040836169914};


    PowerMethod(double[][] matrA) {
        n = matrA.length;
        this.prevX = new double[n];
        this.curX = new double[n];
        this.matrA = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matrA[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    this.matrA[i][j] += matrA[k][i] * matrA[k][j];
                }
            }
            if(i == 0){
                prevX[i] = 1;
            }
            else{
                prevX[i] = 0;
            }
        }
    }
    private void newCurX(){
        curX = multiply(matrA, prevX);
    }

    private void newCurLambda(){
        curLambda = vectorsMultiply(curX, prevX)/vectorsMultiply(prevX,prevX);
    }

    private void newPrevX(){
        for(int i = 0; i < n; i++){
            prevX[i] = curX[i];
        }
    }
    private void newPrevLambda(){
        prevLambda = curLambda;
    }

    private double difference(){
        return Math.abs(curLambda - prevLambda);
    }

    public void powerMethod(){
        while (difference() > epsilon){
            if(count > 0) {
                this.newPrevLambda();
            }
            this.newCurX();
            this.newCurLambda();
            this.newPrevX();
            this.count++;
        }
        System.out.println("count = " + count);
        System.out.println("Lambda = " + curLambda);
        System.out.println("vector X:");
        for(double item: curX){
            System.out.println(item);
        }
    }


    public void printDiscrepancy(){
        double discrepancy = 0;
        for(int j = 0; j < n; j++){
            discrepancy += Math.pow(curLambda, n-j) * polinom[j];
        }
        discrepancy += polinom[n];
        System.out.println("discrepancy for lambda = " + curLambda);
        System.out.format("%25s", discrepancy + "\n");
    }


    private double [] multiply(double[][] matr, double[] vector){
        double result[] = new double[n];
        for(int i = 0; i < n; i++)
        {
            result[i]=0;
            for(int j = 0; j < n; j++)
            {
                result[i] += matr[i][j]*vector[j];
            }
        }
        return result;
    }


    private double[] multiply(double[] vector, double lambda){
        double[] result = new double[n];
        for(int i = 0; i < n; i++){
            result[i] = vector[i] * lambda;
        }
        return result;
    }
    private double[] minus(double[] vectorA, double[] vectorB){
        double[] result = new double[n];
        for(int i = 0; i < n; i++){
            result[i] = vectorA[i] - vectorB[i];
        }
        return result;
    }

    public void printVectorDiscrepancy(){
        System.out.println("\nEigen vectors discrepancy\n");
        for(double item : minus(multiply(matrA, curX), multiply(curX, curLambda)))
        System.out.println(item);
    }



    private double vectorsMultiply(double first[], double second[]) {
        double res = 0;
        for (int i = 0; i < n; i++){
            res += first[i]*second[i];
            i++;
        }
        return res;
    }



}




