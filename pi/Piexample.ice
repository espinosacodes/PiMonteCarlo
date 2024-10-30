module Demo {
    interface Master {
        string sendNumber(int N);
        int receiveWorker(int i);
    }

    interface Worker {
        void receiveNumber(int N);
    }
}