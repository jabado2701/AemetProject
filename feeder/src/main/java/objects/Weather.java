package objects;

    public record Weather(String id, String ubi, String date, double ta, double tmax, double tmin){


        @Override
        public String id() {
            return id;
        }

        @Override
        public String ubi() {
            return ubi;
        }

        @Override
        public String date() {
            return date;
        }

        @Override
        public double ta() {
            return ta;
        }

        @Override
        public double tmax() {
            return tmax;
        }

        @Override
        public double tmin() {
            return tmin;
        }
    }
