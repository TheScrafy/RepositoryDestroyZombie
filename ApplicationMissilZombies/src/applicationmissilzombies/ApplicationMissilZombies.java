/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmissilzombies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author SHIAN
 */
public class ApplicationMissilZombies {

    /**
     * @param args the command line arguments
     */
    private static Integer widthArea = 0;
    private static Integer heightArea = 0;
    private static Boolean validateArea = true;
    private static Boolean validateCoordinate = true;

    public static void main(String[] args) {
        // TODO code application logic here
        List<Coordinate> listCoordinate = new ArrayList<Coordinate>();
        List<Coordinate> listCoordinateMissile = new ArrayList<Coordinate>();

        Scanner sc = new Scanner(System.in);
        while (validateArea) {
            System.out.println("Enter the address of the file to read");
            String address = sc.nextLine();
            loadData(address, listCoordinate);

            if (!validateArea) {
                System.out.println("The document has some error");
                validateArea = true;
            } else {
                validateArea = false;
            }
        }

        while (validateCoordinate) {
            for (int i = 0; i < 3; i++) {
                System.out.println("Enter x coordinate of the missile " + (i + 1));
                Integer missileCoordinateX = sc.nextInt();

                if (missileCoordinateX > widthArea || missileCoordinateX < 1) {
                    validateCoordinate = false;
                    break;
                }

                System.out.println("Enter y coordinate of the missile " + (i + 1));
                Integer missileCoordinateY = sc.nextInt();

                if (missileCoordinateY > heightArea || missileCoordinateY < 1) {
                    validateCoordinate = false;
                    break;
                }
                listCoordinateMissile.add(new Coordinate(missileCoordinateX, missileCoordinateY));
            }
            if (!validateCoordinate) {
                System.out.println("The values are invalid or exceed the area");
                listCoordinateMissile.clear();
                validateCoordinate = true;
            } else {
                validateCoordinate = false;
            }
        }

        showZombieDead(listCoordinateMissile, listCoordinate);
    }

    private static void loadData(String fileAddress, List<Coordinate> listCoordinate) {
        File file = null;
        FileReader fileReader = null;
        BufferedReader bufferReader = null;
        try {
            file = new File(fileAddress);
            if (file.exists()) {
                fileReader = new FileReader(file);
                bufferReader = new BufferedReader(fileReader);

                String line;
                Boolean inicio = true;

                while ((line = bufferReader.readLine()) != null) {
                    String[] linesArray = line.split(" ");
                    if (inicio) {
                        widthArea = Integer.valueOf(linesArray[0]);
                        heightArea = Integer.valueOf(linesArray[1]);
                        if (widthArea == heightArea) {
                            validateArea = false;
                            break;
                        }
                        inicio = false;
                    } else {
                        Coordinate coordinate = new Coordinate();
                        coordinate.setPosX(Integer.valueOf(linesArray[0]));
                        coordinate.setPosY(Integer.valueOf(linesArray[1]));
                        coordinate.setCantZombie(Integer.valueOf(linesArray[2]));
                        if (coordinate.getCantZombie() == 0 || coordinate.getPosX() > widthArea || coordinate.getPosX() < 1 || coordinate.getPosY() > heightArea || coordinate.getPosY() < 1) {
                            validateArea = false;
                            break;
                        }
                        listCoordinate.add(coordinate);
                    }
                }
            } else {
                validateArea = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void showZombieDead(List<Coordinate> listCoordinateMissil, List<Coordinate> listCoordinate) {
        String messageResult = "";
        for (int i = 0; i < listCoordinateMissil.size(); i++) {
            Integer positionMissilX = listCoordinateMissil.get(i).getPosX();
            Integer positionMissilY = listCoordinateMissil.get(i).getPosY();
            messageResult += positionMissilX + " " + positionMissilY + " " + calculatingZombieDead(positionMissilX, positionMissilY, listCoordinate) + "\n";
        }
        System.out.println(messageResult);
    }

    private static Integer calculatingZombieDead(Integer posX, Integer posY, List<Coordinate> listCoordinate) {
        Integer cantZombieDead = 0;
        for (int i = 0; i < 5; i++) {
            Integer positionX = 0;
            Integer positionY = 0;
            switch (i) {
                case 0:
                    positionX = posX - 2;
                    positionY = posY;
                    cantZombieDead += searchCoordinate(positionX, positionY, listCoordinate);
                    break;
                case 1:
                    for (int j = 0; j < 3; j++) {
                        positionX = posX - 1;
                        positionY = ((posY + 1) - j);
                        cantZombieDead += searchCoordinate(positionX, positionY, listCoordinate);
                    }
                    break;
                case 2:
                    for (int j = 0; j < 5; j++) {
                        positionX = posX;
                        positionY = ((posY + 2) - j);
                        cantZombieDead += searchCoordinate(positionX, positionY, listCoordinate);
                    }
                    break;
                case 3:
                    for (int j = 0; j < 3; j++) {
                        positionX = posX + 1;
                        positionY = ((posY + 1) - j);
                        cantZombieDead += searchCoordinate(positionX, positionY, listCoordinate);
                    }
                    break;
                case 4:
                    positionX = posX + 2;
                    positionY = posY;
                    cantZombieDead += searchCoordinate(positionX, positionY, listCoordinate);
                    break;
            }
        }
        return cantZombieDead;
    }

    private static Integer searchCoordinate(Integer posX, Integer posY, List<Coordinate> listCoordinate) {
        Integer cantZombie = 0;
        for (Coordinate itemCoordinate : listCoordinate) {
            if (posX.equals(itemCoordinate.getPosX()) && posY.equals(itemCoordinate.getPosY())) {
                cantZombie = itemCoordinate.getCantZombie();
            }
        }
        return cantZombie;
    }
}
