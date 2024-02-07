package edu.iu.mbarrant.C322Homework3.model;

public record DuckData(int id, Duck.Type type) {

    public String toLine() {
        return String.format("%1$s,%2$s", id(), type()); // Use type() instead of type
    }

    public static DuckData fromLine(String line) {
        String[] tokens = line.split(",");
        return new DuckData(Integer.parseInt(tokens[0]), Duck.Type.valueOf(tokens[1])); // Use Duck.Type.valueOf(tokens[1]) instead of tokens[1]
    }
}
