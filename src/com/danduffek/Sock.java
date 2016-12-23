package com.danduffek;

/**
 * Created by Dan on 12/22/2016.
 */
public class Sock implements Comparable<Sock>{
    private String _size;
    private String _color;

    Sock(Size size, Color color)
    {
        _size = size.get_value();
        _color = color.get_value();
    }

    public String get_size()
    {
        return _size;
    }

    public String get_color()
    {
        return _color;
    }

    public int compareTo(Sock other) {
        return _color.compareTo(other._color);
    }

    public enum Size
    {
        ADULT("a"),
        CHILD("c");

        private String _value;

        Size(String value)
        {
            _value = value;
        }

        public String get_value()
        {
            return _value;
        }
    }

    public enum Color
    {
        RED("r"),
        BLUE("b");

        private String _value;

        Color(String value)
        {
            _value = value;
        }

        public String get_value()
        {
            return _value;
        }
    }
}
