package io.github.atimothee.sunshine.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;











    public class Model {

        @Expose
        private String cod;
        @Expose
        private Double message;
        @Expose
        private City city;
        @Expose
        private Integer cnt;
        @Expose
        private java.util.List<io.github.atimothee.sunshine.data.List> list = new ArrayList<io.github.atimothee.sunshine.data.List>();

        /**
         * @return The cod
         */
        public String getCod() {
            return cod;
        }

        /**
         * @param cod The cod
         */
        public void setCod(String cod) {
            this.cod = cod;
        }

        /**
         * @return The message
         */
        public Double getMessage() {
            return message;
        }

        /**
         * @param message The message
         */
        public void setMessage(Double message) {
            this.message = message;
        }

        /**
         * @return The city
         */
        public City getCity() {
            return city;
        }

        /**
         * @param city The city
         */
        public void setCity(City city) {
            this.city = city;
        }

        /**
         * @return The cnt
         */
        public Integer getCnt() {
            return cnt;
        }

        /**
         * @param cnt The cnt
         */
        public void setCnt(Integer cnt) {
            this.cnt = cnt;
        }

        /**
         * @return The list
         */
        public java.util.List<io.github.atimothee.sunshine.data.List> getList() {
            return list;
        }

        /**
         * @param list The list
         */
        public void setList(java.util.List<io.github.atimothee.sunshine.data.List> list) {
            this.list = list;
        }

        public class Sys {

            @Expose
            private Integer population;

            /**
             * @return The population
             */
            public Integer getPopulation() {
                return population;
            }

            /**
             * @param population The population
             */
            public void setPopulation(Integer population) {
                this.population = population;
            }

        }


        public class Temp {

            @Expose
            private Double day;
            @Expose
            private Double min;
            @Expose
            private Double max;
            @Expose
            private Double night;
            @Expose
            private Double eve;
            @Expose
            private Double morn;

            /**
             * @return The day
             */
            public Double getDay() {
                return day;
            }

            /**
             * @param day The day
             */
            public void setDay(Double day) {
                this.day = day;
            }

            /**
             * @return The min
             */
            public Double getMin() {
                return min;
            }

            /**
             * @param min The min
             */
            public void setMin(Double min) {
                this.min = min;
            }

            /**
             * @return The max
             */
            public Double getMax() {
                return max;
            }

            /**
             * @param max The max
             */
            public void setMax(Double max) {
                this.max = max;
            }

            /**
             * @return The night
             */
            public Double getNight() {
                return night;
            }

            /**
             * @param night The night
             */
            public void setNight(Double night) {
                this.night = night;
            }

            /**
             * @return The eve
             */
            public Double getEve() {
                return eve;
            }

            /**
             * @param eve The eve
             */
            public void setEve(Double eve) {
                this.eve = eve;
            }

            /**
             * @return The morn
             */
            public Double getMorn() {
                return morn;
            }

            /**
             * @param morn The morn
             */
            public void setMorn(Double morn) {
                this.morn = morn;
            }

        }


        public class Weather {

            @Expose
            private Integer id;
            @Expose
            private String main;
            @Expose
            private String description;
            @Expose
            private String icon;

            /**
             * @return The id
             */
            public Integer getId() {
                return id;
            }

            /**
             * @param id The id
             */
            public void setId(Integer id) {
                this.id = id;
            }

            /**
             * @return The main
             */
            public String getMain() {
                return main;
            }

            /**
             * @param main The main
             */
            public void setMain(String main) {
                this.main = main;
            }

            /**
             * @return The description
             */
            public String getDescription() {
                return description;
            }

            /**
             * @param description The description
             */
            public void setDescription(String description) {
                this.description = description;
            }

            /**
             * @return The icon
             */
            public String getIcon() {
                return icon;
            }

            /**
             * @param icon The icon
             */
            public void setIcon(String icon) {
                this.icon = icon;
            }

        }

    }



