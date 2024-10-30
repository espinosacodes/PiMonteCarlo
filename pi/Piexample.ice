module Demo {
    interface Master {
        string distributeWork(int N);
        double getResult();
        int receiveWorker(int i);
    }

    interface Worker {
        int calculatePointsInCircle(int pointsToGenerate);
    }
}