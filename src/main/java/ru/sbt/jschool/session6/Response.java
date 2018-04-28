package ru.sbt.jschool.session6;

/**
 * Created by 1 on 27.04.2018.
 */
public enum Response {

    OK{
        @Override
        public String toString() {
            return "200 OK";
        }
    },

    NOT_FOUND{
        @Override
        public String toString() {
            return "404 NOT FOUND";
        }
    },

    BAD_REQUEST{
        @Override
        public String toString() {
            return "400 BAD REQUEST";
        }
    },

}
