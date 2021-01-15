import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WarmUpTask extends Traversal{





    static float moive_helper[][];
    static void movie_helper_filler(){
        ////part-1: traverse the rating
        for(int i=0;i<list_rates.size();i++){
            rate curr=list_rates.get(i);

            int mid=curr.mid;
            int rat= curr.rat;

            moive_helper[mid][0]+=rat;
            moive_helper[mid][1]++;
            if(moive_helper[mid][1]>0){
                moive_helper[mid][2]=moive_helper[mid][0]/moive_helper[mid][1];
            }
        }
    }
//    public static void main(String args[]) throws IOException {
//
//        ////compulsory things to do
//        getRating("path");
//        getMovie("path");
//        getGenera("path");
//
//        ////most rated movies on average and frequency
//        moive_helper=new float[1683][3];//mid value freq avg_value
//        movie_helper_filler();
//
//         //1   topMoviesByGenre();
//        //2  topMovieByYear();
//        //3  topMovieYearGenra();
//        //4  mostWatchedMovie();
//        //5 mostWatchedGenra();
//        //6  mostRatedGenra();
//       //7   mostActiveUser();
//    }
    static void WarmUpTaskInititaliszer() throws IOException{
        ////compulsory things to do
        getRating("path");
        getMovie("path");
        getGenera("path");

        ////most rated movies on average and frequency
        moive_helper=new float[1683][3];//mid value freq avg_value
        movie_helper_filler();
    }






    //////Task 1: Top Movies By Genre
    static void topMoviesByGenre(){
        ///part-1: get help_for_movies(done already)
        ///part -2: store id of top movie in topMovieArray
        int topMovieGenre[]=new int[19];
        ////part -3: traverse movie helper to fill topMovieGenre
        for(int i=0;i<1683;i++){
            if(Movie.containsKey(i)){

                ArrayList<Integer>genOfMovie=Movie.get(i).gen;

              for(int j=0;j<genOfMovie.size();j++){
                  int genra=genOfMovie.get(j);
                  if(moive_helper[topMovieGenre[genra]][2]<moive_helper[i][2]){
                      topMovieGenre[genra]=i;
                  }
                  else if(moive_helper[topMovieGenre[genra]][2]==moive_helper[i][2] && moive_helper[topMovieGenre[genra]][1]==moive_helper[i][1]){
                      topMovieGenre[genra]=i;
                  }

              }


            }

        }
        ////part -4: print answer
        for(int i=0;i<19;i++){
            System.out.println("For genra "+Genre.get(i)+" highest movie is :"+Movie.get(topMovieGenre[i]).title+" .");
        }

    }




    ////Task 2: Movies By year
    static void topMovieByYear(){

        //Part - 1: Make HashMap :(year:mid)
        HashMap<Integer,Integer>topMovieYear=new HashMap<Integer,Integer>();
        /// Part -2: get range for HashMap
        int larg_year=0;
        int small_year=0;
        /// Part -3: Traverse Movies helper and get year from Movie
        for(int i=0;i<1683;i++){
            if(Movie.containsKey(i)){

               String date=Movie.get(i).rdate;
               String dateBroken[]=date.split("-");
               if(dateBroken.length>2 && dateBroken[2].length()==4){
                   int year=Integer.parseInt(dateBroken[2]);
                   larg_year=Math.max(larg_year,year);
                   small_year=Math.min(small_year,year);

                   if(!topMovieYear.containsKey(year)){
                       topMovieYear.put(year,i);
                   }
                   else {
                       int prevMovie=topMovieYear.get(year);
                       if(moive_helper[prevMovie][2]<moive_helper[i][2]){//average value
                           topMovieYear.put(year,i);
                       }
                       else if(moive_helper[prevMovie][2]==moive_helper[i][2] && moive_helper[prevMovie][1]==moive_helper[i][1]){ //frequnecy
                           topMovieYear.put(year,i);
                       }

                   }

               }



            }

        }

        ///Part 4:  print Anwer
        for(int i=small_year;i<=larg_year;i++){
            if(topMovieYear.containsKey(i)){
                System.out.println(i+" year has it best movie : "+Movie.get(topMovieYear.get(i)).title);
            }
        }

    }




    ///Task 3: Movie by year and gen
    static void topMovieYearGenra(){

        ////Part 1: create HashMap topYearGenra (year arr[19])
        HashMap<Integer,int []>topYearGenra=new HashMap<>();
        ///Part 2: Traverse movie_helper get mid , its average score ,its year from Movies , its genra list
        for(int i=0;i<1683;i++){
            if(Movie.containsKey(i)){
                movie curr=Movie.get(i);


                /// Part 3: get year of movie

                String date=Movie.get(i).rdate;
                String dateBroken[]=date.split("-");
                if(dateBroken.length>2 && dateBroken[2].length()==4) {
                    int year = Integer.parseInt(dateBroken[2]);
                    ArrayList<Integer> genraList=curr.gen;
                    for(int j=0;j<genraList.size();j++){
                        int G=genraList.get(j);
                        float scoreOfMovie=moive_helper[i][2];

                        if(topYearGenra.containsKey(year)){

                            ///update the arrayList of genra for year which contains mid
                            int []arr=topYearGenra.get(year);
                            for(int k=0;k<19;k++){
                                if(scoreOfMovie>moive_helper[arr[k]][2]){
                                    arr[k]=i;

                                }
                            }
                            ////
                            topYearGenra.put(year,arr);

                        }
                        else{

                            //create new array and put in for year
                            int []arr=new int[19];
                            Arrays.fill(arr,i);
                            topYearGenra.put(year,arr);

                        }


                    }

                }

                }


        }
        ///Part 4: Print anser  1933-1998
        for(int i=1933;i<=1998;i++){
            if(topYearGenra.containsKey(i)){
                System.out.println("Top Movie in year : "+i+"  are :-");
                int []arr=topYearGenra.get(i);
                for(int j=0;j<19;j++){
                    System.out.println(Movie.get(arr[j]).title+" in "+Genre.get(j));
                }

            }
        }





    }



    /////Task 4: Most watched Movie
    static void mostWatchedMovie(){
        ///Part 1: treavers movie helper
        ///Part 2: keep record of most watched frequency and mid related to it
        int bestMovie=0;
        float bestScore=0;
        for(int i=0;i<1683;i++){
            if(Movie.containsKey(i)){
                if(moive_helper[i][1]>bestScore){
                    bestScore=moive_helper[i][1];
                    bestMovie=i;
                }
            }

        }
        ///Part 3:Print Anwer
        System.out.println("highest Watched movie is : "+Movie.get(bestMovie).title);


    }




    ////Task 5: Most watched Genra
    static void mostWatchedGenra(){

        ///Part 1: Keep record of frequency for each genera
        float genra_score[]=new float[19];
        //Part 2: travers Movie Helper get frequency and mid

        int bestGenra=0;
        float bestScore=0;
        for(int i=0;i<1683;i++){
            if(Movie.containsKey(i)){
                ///Part 3:Update frequency of All generas related to mid
                ArrayList<Integer>genraList=Movie.get(i).gen;
                for(int j=0;j<genraList.size();j++){
                    int G=genraList.get(j);
                    genra_score[G]+=moive_helper[i][1];

                    if(genra_score[G]>bestScore){
                        bestScore=genra_score[G];
                        bestGenra=G;
                    }
                }
            }

        }
        ///Part 4:Print Anwer
        System.out.println("highest Watched genre is : "+Genre.get(bestGenra));

    }




    /// Task 6: Most Rated Genra
    static void mostRatedGenra(){

        ///Part 1: Keep record of score for each genera
        float genra_score[]=new float[19];
        //Part 2: travers Movie Helper get score and mid

        int bestGenra=0;
        float bestScore=0;
        for(int i=0;i<1683;i++){
            if(Movie.containsKey(i)){
                ///Part 3:Update score of All generas related to mid
                ArrayList<Integer>genraList=Movie.get(i).gen;
                for(int j=0;j<genraList.size();j++){
                    int G=genraList.get(j);
                    genra_score[G]+=moive_helper[i][2];

                    if(genra_score[G]>bestScore){
                        bestScore=genra_score[G];
                        bestGenra=G;
                    }
                }
            }

        }
        ///Part 4:Print Anwer
        System.out.println("highest Rated genre is : "+Genre.get(bestGenra));

    }




    ///Task 7: Most Active user
    static void mostActiveUser(){
        ///Part 1 : make hashMap for (uid  frequnecy);
        HashMap<Integer,Integer>mostActiveUID=new HashMap<Integer,Integer>();
        ////Part 2: travese rating list
        int mostActive=0;
        int mostFrequency=0;

        for(int i=0;i<list_rates.size();i++){
            rate curr=list_rates.get(i);
            int uid=curr.uid;
            if(!mostActiveUID.containsKey(uid)){
                mostActiveUID.put(uid,1);
            }
            else{
                mostActiveUID.put(uid,mostActiveUID.get(uid)+1);
            }
            if(mostActiveUID.get(uid)>mostFrequency){
                mostActive=uid;
                mostFrequency=mostActiveUID.get(uid);
            }

        }
        System.out.println("Most Active use is :"+mostActive);



    }


}
