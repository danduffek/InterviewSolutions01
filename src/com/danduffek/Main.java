package com.danduffek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        findUniqueProblem();

        sockProblem();
    }

    private static void findUniqueProblem()
    {
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        List<Integer> list1 = Arrays.asList(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);

        Collections.sort(list1);
        Collections.sort(list2);

        List<Integer> unique = findUnique(list1, list2);

        for(int i : unique)
        {
            System.out.println(i);
        }

        unique = findUnique01(list1, list2);

        for(int i : unique)
        {
            System.out.println(i);
        }
    }

    private static List<Integer> findUnique(List<Integer> list1, List<Integer> list2)
    {
        List<Integer> listOut = new ArrayList<>();
        int jStart = 0;

        for(int i=0; i < list1.size(); i++)
        {

            if((jStart < list2.size()))
            {
                if (list1.get(i) != list2.get(jStart)) {
                    if (list1.get(i) < list2.get(jStart)) {
                        listOut.add(list1.get(i));
                    } else {
                        for (int j = jStart; j < list2.size(); j++) {

                            if (list1.get(i) != list2.get(j)) {
                                if (list2.get(j) < list1.get(i)) {
                                    listOut.add(list2.get(j));
                                } else {
                                    jStart = j;
                                    break;
                                }
                            } else {
                                jStart = j + 1;
                                break;
                            }
                        }
                    }
                } else {
                    jStart++;
                }
            }
            else
            {
                listOut.add(list1.get(i));
            }
        }

        for(int j1 = jStart; j1 < list2.size(); j1++)
            listOut.add(list2.get(j1));

        return listOut;
    }

    private static List<Integer> findUnique01(List<Integer> list1, List<Integer> list2)
    {
        List<Integer> singleList = new ArrayList<>();

        list1 = removeDupes(list1);
        list2 = removeDupes(list2);

        singleList.addAll(list1);
        singleList.addAll(list2);

        Collections.sort(singleList);

        return removeDupes(singleList);
    }

    private static List<Integer> removeDupes(List<Integer> listIn)
    {
        List<Integer> listOut = new ArrayList<>();

        for(int i=0; i < listIn.size() - 1; )
        {
            if(listIn.get(i) != listIn.get(i+1))
            {
                listOut.add(listIn.get(i));
                i++;
            }
            else
            {
                i+= 2;
            }
        }

        if(listIn.get(listIn.size() - 2) != listIn.get(listIn.size() - 1))
            listOut.add(listIn.get(listIn.size()-1));

        return listOut;
    }

    private static void sockProblem()
    {
        List<Sock> basket = new ArrayList<>();

        basket.add(new Sock(Sock.Size.ADULT, Sock.Color.BLUE));
        basket.add(new Sock(Sock.Size.ADULT, Sock.Color.BLUE));
        basket.add(new Sock(Sock.Size.ADULT, Sock.Color.BLUE));
        basket.add(new Sock(Sock.Size.ADULT, Sock.Color.BLUE));
        basket.add(new Sock(Sock.Size.ADULT, Sock.Color.RED));
        basket.add(new Sock(Sock.Size.ADULT, Sock.Color.RED));
        basket.add(new Sock(Sock.Size.CHILD, Sock.Color.BLUE));
        basket.add(new Sock(Sock.Size.CHILD, Sock.Color.BLUE));
        basket.add(new Sock(Sock.Size.CHILD, Sock.Color.RED));
        basket.add(new Sock(Sock.Size.CHILD, Sock.Color.RED));

        Collections.shuffle(basket);

        findPairs(basket);
    }

    private static void findPairs(List<Sock> mixedBasket)
    {
        List<Sock> adultSocks = new ArrayList<>();
        List<Sock> childSocks = new ArrayList<>();
        List<Sock> unMatched = new ArrayList<>();

        System.out.println("mixed at start:");
        printSockList(mixedBasket);

        for(Sock sock : mixedBasket)
        {
            if(sock.get_size().equals(Sock.Size.ADULT.get_value()))
                adultSocks.add(sock);
            else
                childSocks.add(sock);
        }

        System.out.println("Adult before sort:");
        printSockList(adultSocks);

        Collections.sort(adultSocks);
        Collections.sort(childSocks);

        System.out.println("Adult after sort:");
        printSockList(adultSocks);

        for(int i=0; i < adultSocks.size(); )
        {
            if((i == adultSocks.size()-1) ||
            (!adultSocks.get(i).get_color().equals(adultSocks.get(i+1).get_color())))
            {
                unMatched.add(adultSocks.get(i));
                adultSocks.remove(i);
            }
            else
            {
                i += 2;
            }
        }

        for(int i=0; i < childSocks.size(); )
        {
            if((i == childSocks.size()-1) ||
            (!childSocks.get(i).get_color().equals(childSocks.get(i+1).get_color())))
            {
                unMatched.add(childSocks.get(i));
                childSocks.remove(i);
            }
            else
            {
                i += 2;
            }
        }

        System.out.println("Adult Socks:");
        printSockList(adultSocks);
        System.out.println();
        System.out.println("Child Socks:");
        printSockList(childSocks);
        System.out.println();
        System.out.println("UnMatched Socks:");
        printSockList(unMatched);
    }

    static private void printSockList(List<Sock> list)
    {
        for(Sock sock : list)
        {
            System.out.println(sock.get_size() + " " + sock.get_color());
        }
    }
}
