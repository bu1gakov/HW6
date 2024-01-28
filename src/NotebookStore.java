import java.util.ArrayList;
import java.util.List;

public class NotebookStore {
    private List<Notebook> notebooks;

    public NotebookStore() {
        notebooks = new ArrayList<>();
    }

    private void addNotebook(Notebook notebook) {
        notebooks.add(notebook);
    }

    private List<Notebook> getNotebooks() {
        return notebooks;
    }

    private List<Notebook> filterNotebooks(NotebookFilter filter) {
        List<Notebook> filteredNotebooks = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            if (filter.matches(notebook)) {
                filteredNotebooks.add(notebook);
            }
        }
        return filteredNotebooks;
    }

    public static void main(String[] args) {
        NotebookStore store = new NotebookStore();

        Notebook notebook1 = new Notebook("Dell", "XPS 13", 256, "Grey", "Windows 10", 8);
        Notebook notebook2 = new Notebook("Apple", "MacBook Air", 128, "Silver", "Mac OS", 8);
        Notebook notebook3 = new Notebook("Lenovo", "ThinkPad X1 Carbon", 512, "Black", "Ubuntu", 16);
        Notebook notebook4 = new Notebook("Asus", "ROG Strix 18", 1024, "Black", "no OS", 32);
        Notebook notebook5 = new Notebook("MSI", "Titan GT77", 3072, "Black", "Red OS", 64);

        store.addNotebook(notebook1);
        store.addNotebook(notebook2);
        store.addNotebook(notebook3);
        store.addNotebook(notebook4);
        store.addNotebook(notebook5);

        System.out.println("Notebooks list:");
        for (Notebook notebook : store.getNotebooks()) {
            System.out.println(notebook);
        }

        NotebookFilter filter = new NotebookFilter.Builder()
                .withHdd(256)
                .withColor("Black")
                .withRam(8)
                .withOs("Windows 10")
                .build();

        System.out.println("\nFilter results:");
        for (Notebook notebook : store.filterNotebooks(filter)) {
            System.out.println(notebook);
        }
    }
}

class Notebook {
    String brand;
    String model;
    String color;
    String os;
    int hdd;
    int ram;

    public Notebook(String brand, String model, int hdd, String color, String os, int ram) {
        this.brand = brand;
        this.model = model;
        this.hdd = hdd;
        this.color = color;
        this.os = os;
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "Brand: " + brand + "; Model: " + model + "; HDD: " + hdd + "GB; Color: " + color + "; OS: " + os
                + "; Ram: " + ram + "GB";
    }
}

class NotebookFilter {
    private Integer hdd;
    private Integer ram;
    private String color;
    private String os;

    private NotebookFilter(Integer hdd, String color, Integer ram, String os) {
        this.hdd = hdd;
        this.color = color;
        this.ram = ram;
        this.os = os;
    }

    public boolean matches(Notebook notebook) {
        if (hdd != null && !hdd.equals(notebook.hdd)) {
            return false;
        }
        if (color != null && !color.equals(notebook.color)) {
            return false;
        }
        if (ram != null && !ram.equals(notebook.hdd)) {
            return false;
        }
        return os == null || os.equals(notebook.os);
    }

    public static class Builder {
        private Integer hdd;
        private Integer ram;
        private String os;
        private String color;

        public Builder withHdd(int hdd) {
            this.hdd = hdd;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder withOs(String os) {
            this.os = os;
            return this;
        }

        public NotebookFilter build() {
            return new NotebookFilter(hdd, color, ram, os);
        }
    }
}    