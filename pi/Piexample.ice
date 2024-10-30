module Demo {
    interface Master {
        
        string sendNumber(int N);
    }

    interface Worker {
        void receiveNumber(int N);
    }
}