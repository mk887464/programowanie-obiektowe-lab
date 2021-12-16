//package agh.ics.oop;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//class SimulationEngine implements IEngine{
//    private final IWorldMap map;
//    private final ArrayList<MoveDirection> moves;
//    private final List<Animal> animals = new ArrayList<>();
//
//
//    public SimulationEngine(ArrayList<MoveDirection> moves, IWorldMap map, Vector2d[] startPositions){
//        this.moves=moves;
//        this.map = map;
//        initializeMap(startPositions);
//    }
//
//    private void initializeMap(Vector2d[] startPositions){
//        for(Vector2d position : startPositions){
//            Animal animal = new Animal(this.map,position);
//            if (this.map.place(animal)){
//                animals.add(animal);
//            }
//        }
//    }
//
//    public void run(){
//        int len = this.moves.size();
//        int i = 0;
//        while (i < len) {
//            for (Animal animal : animals) {
//                animal.move(moves.get(i));
//                System.out.println(moves.get(i));
//                System.out.println(animal.getPosition());
//                System.out.println(map);
//                i++;
//            }
//        }
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SimulationEngine that = (SimulationEngine) o;
//        return Objects.equals(map, that.map) && Objects.equals(moves, that.moves) && Objects.equals(animals, that.animals);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(map, moves, animals);
//    }
//}


//
//package agh.ics.oop;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class SimulationEngine implements IEngine{
//    private final IWorldMap map;
//    private final ArrayList<MoveDirection> moves;
//    private final List<Animal> animals = new ArrayList<>();
//
//
//    public SimulationEngine(ArrayList<MoveDirection> moves, IWorldMap map, Vector2d[] startPositions){
//        this.moves=moves;
//        this.map = map;
//        initializeMap(startPositions);
//    }
//
//
//    private void initializeMap(Vector2d[] startPositions){
//        for(Vector2d position : startPositions){
//            Animal animal = new Animal(this.map,position);
//            if (this.map.place(animal)){
//                animals.add(animal);
//            }
//       }
//    }
//
//
//   public void run(){
//       int len = this.moves.size();
//       int i = 0;
//       while (i < len) {
//            for (Animal animal : animals) {
//               animal.move(moves.get(i));
//                System.out.println(moves.get(i));
//               System.out.println(animal.getPosition());
//                System.out.println(map);
//                i++;
//           }
//       }
//    }
//}


//package agh.ics.oop;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//class SimulationEngine implements IEngine{
//    private final IWorldMap map;
//    private final ArrayList<MoveDirection> moves;
//    private final List<Animal> animals = new ArrayList<>();
//
//
//    public SimulationEngine(ArrayList<MoveDirection> moves, IWorldMap map, Vector2d[] startPositions){
//        this.moves=moves;
//        this.map = map;
//        initializeMap(startPositions);
//    }
//
//
//    private void initializeMap(Vector2d[] startPositions){
//        for(Vector2d position : startPositions){
//            Animal animal = new Animal(this.map,position);
//            if (this.map.place(animal)){
//                animals.add(animal);
//            }
//        }
//    }
//
//
//    public void run(){
//        int len = this.moves.size();
//        int i = 0;
//        while (i < len) {
//            for (Animal animal : animals) {
//                animal.move(moves.get(i));
//                System.out.println(moves.get(i));
//                System.out.println(animal.getPosition());
//                System.out.println(map);
//                i++;
//            }
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SimulationEngine that = (SimulationEngine) o;
//        return Objects.equals(map, that.map) && Objects.equals(moves, that.moves) && Objects.equals(animals, that.animals);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(map, moves, animals);
//    }
//}



package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine,Runnable {
    private ArrayList<MoveDirection> directions; // final?
    private final IWorldMap map;
    private final App app;


    public SimulationEngine(ArrayList<MoveDirection> directions, IWorldMap map, Vector2d[] initialPositions, Application app){
        this.directions = directions;
        this.map = map;
        this.app = (App) app;
        initializeMap(initialPositions);
    }

    public void getDirections(ArrayList<MoveDirection> directions) {
        this.directions = directions;
    }

//    public void setDirections(ArrayList<MoveDirection> directions) {
//        this.directions = directions;
//    }
//
//    public SimulationEngine(AbstractWorldMap map, Vector2d[] initialPositions, Application app) {
//        this.map = map;
//        this.app = (App) app;
//        initializeMap(initialPositions);
//    }

    private void initializeMap(Vector2d[] initialPositions){
        for (Vector2d position : initialPositions){
            map.place(new Animal(this.map, position));
//            animal.addObserver(this.map);
        }
    }


//    public void run(){
//        out.println(this.map);
//        int i = 0;
//        while (i < directions.size()) {
//            for (Animal animal : animals) {
//                animal.move(directions.get(i));
//
//                out.println(directions.get(i).toString());
//                out.println(map);
//                i++;
//                if (i >= directions.size()) break;
//            }
//        }
//    }


    @Override
    public void run() {
        try {
            int moveDelay = 500;
            Thread.sleep(moveDelay);
        } catch (InterruptedException e) {
            System.out.println("Thread.sleep error: " + e);
        }
        Animal[] animals = this.map.getAnimals().values().toArray(new Animal[0]);
        int len = animals.length;
        int i = 0;

        for (MoveDirection direction : this.directions) {
            animals[i % len].move(direction);
            i++;
            System.out.println(this.map);
            Platform.runLater(() -> {
                app.positionChanged(new Animal(map), new Vector2d(1, 1), new Vector2d(1, 1));
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread.sleep error: " + e);
            }
        }
    }
}
