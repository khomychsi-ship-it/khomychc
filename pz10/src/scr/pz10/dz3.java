package semester1.practice10;

public class dz3 {

    interface Evaluable1D {
        double EPSILON = 1e-7;
        double evaluate(double x);
    }

    interface Integrable1D extends Evaluable1D {
        default double integrateSimpson(double a, double b, int n) {
            if (n < 2) {
                n = 100;
            }
            if (n % 2 != 0) {
                n++;
            }

            double h = (b - a) / n;
            double sum = evaluate(a) + evaluate(b);

            for (int i = 1; i < n; i++) {
                double x = a + i * h;

                if (i % 2 == 0) {
                    sum = sum + 2 * evaluate(x);
                } else {
                    sum = sum + 4 * evaluate(x);
                }
            }

            return sum * h / 3;
        }
    }

    interface Differentiable1D extends Evaluable1D {
        double derivative(double x);

        default double numericalDerivative(double x) {
            double h = 0.00001;
            return (evaluate(x + h) - evaluate(x - h)) / (2 * h);
        }
    }

    interface NewtonRootFindable extends Differentiable1D {
        default double findRootNewton(double x, int maxIter) {
            for (int i = 0; i < maxIter; i++) {
                if (Math.abs(evaluate(x)) < EPSILON) {
                    return x;
                }

                if (Math.abs(derivative(x)) < 1e-12) {
                    return x;
                }

                x = x - evaluate(x) / derivative(x);
            }
            return x;
        }
    }

    abstract static class AbstractMathFunction implements Integrable1D {
        private final String formulaTitle;

        AbstractMathFunction(String formulaTitle) {
            this.formulaTitle = formulaTitle;
        }

        public final String getFormulaTitle() {
            return formulaTitle;
        }
    }

    public static final class PolynomialFunction extends AbstractMathFunction
            implements NewtonRootFindable {
        private final double[] coeffs;

        PolynomialFunction(String title, double[] coeffs) {
            super(title);
            this.coeffs = coeffs.clone();
        }

        public double evaluate(double x) {
            double result = 0;
            for (int i = 0; i < coeffs.length; i++) {
                result = result + coeffs[i] * Math.pow(x, i);
            }
            return result;
        }

        public double derivative(double x) {
            double result = 0;
            for (int i = 1; i < coeffs.length; i++) {
                result = result + i * coeffs[i] * Math.pow(x, i - 1);
            }
            return result;
        }
    }

    static class DampedHarmonyFunction extends AbstractMathFunction implements NewtonRootFindable {
        private final double amplitude;
        private final double decayRate;
        private final double omega;

        DampedHarmonyFunction(String title, double amplitude, double decayRate, double omega) {
            super(title);
            this.amplitude = amplitude;
            this.decayRate = decayRate;
            this.omega = omega;
        }

        public double evaluate(double x) {
            return amplitude * Math.exp(-decayRate * x) * Math.cos(omega * x);
        }

        public double derivative(double x) {
            return -amplitude * Math.exp(-decayRate * x)
                    * (decayRate * Math.cos(omega * x) + omega * Math.sin(omega * x));
        }
    }

    static class StepHeavisideFunction extends AbstractMathFunction {
        private final double threshold;

        StepHeavisideFunction(String title, double threshold) {
            super(title);
            this.threshold = threshold;
        }

        public double evaluate(double x) {
            if (x < threshold) {
                return 2.0;
            }
            return 5.0;
        }
    }

    public static void main(String[] args) {
        AbstractMathFunction[] functions = {
                new PolynomialFunction("P(x) = x^3 - 2x - 5", new double[]{-5, -2, 0, 1}),
                new DampedHarmonyFunction("D(x) = 4 exp(-0.5x) cos(2x)", 4, 0.5, 2),
                new StepHeavisideFunction("Step(x)", 1.5)
        };

        for (int i = 0; i < functions.length; i++) {
            AbstractMathFunction fn = functions[i];
            int n = 100;

            if (i == 2) {
                n = 1000;
            }

            System.out.println("\nФункція: " + fn.getFormulaTitle());
            System.out.println("Інтеграл: " + fn.integrateSimpson(0, 3, n));

            if (fn instanceof NewtonRootFindable) {
                NewtonRootFindable f = (NewtonRootFindable) fn;
                double x = 2.0;

                if (i == 1) {
                    x = 0.7;
                }

                System.out.println("Похідна: " + f.derivative(1.0));
                System.out.println("Чисельна похідна: " + f.numericalDerivative(1.0));
                System.out.println("Корінь: " + f.findRootNewton(x, 100));
            } else {
                System.out.println("Функція не має похідної, тому корінь не шукаємо.");
            }
        }
    }
}
