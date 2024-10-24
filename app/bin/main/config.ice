module MonteCarlo {
    interface Maestro {
        double estimarPi(int N);
    };

    interface Trabajador {
        int simular(int puntos);
    };
};
