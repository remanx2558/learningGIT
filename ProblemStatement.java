import java.io.IOException;
import java.util.*;

public class ProblemStatement extends WarmUpTask{

    static float[][] movie_helper_2;
    public static void main(String args[]) throws IOException {
        //import task to do first
        WarmUpTaskInititaliszer();
        movie_helper_2=moive_helper;

        ///Part 1:General ordering of movies based on rating as WHAT ALL LIKES HE MAY LIKE
        initialise_mov_sorted_rating();
        ///Part 2: Map movies to Users for faster Access (uid : array_movies[])
        User_Movie_Mapping();
        ///Part 3: Get Recommended basis of UID
        System.out.println("Number of People for which Recommendattion needed");
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        for(int p=0;p<n;p++){
            System.out.println("Enter Uid for User :");
            int uid=s.nextInt();
            GetMovies(uid);
        }


    }



    /////////Part 1 : general sorted movies on basis of average rating
    static Integer mov_sorted_rating[];
    static void initialise_mov_sorted_rating(){

        mov_sorted_rating=new Integer[1683];
        for(int i=0;i<1683;i++){
            mov_sorted_rating[i]=i;
        }
       Arrays.sort(mov_sorted_rating,new basedOnRating());

    }
    static class basedOnRating implements Comparator<Integer> {

        ////avg_value ---->  frequency ----> natural_order
        @Override
        public int compare(Integer a, Integer b) {
            int k=(movie_helper_2[a][2]>movie_helper_2[b][2])?1:-1;
            if(movie_helper_2[a][2]==movie_helper_2[b][2]){
                k=(movie_helper_2[a][1]>movie_helper_2[b][1])?1:-1;
                if(movie_helper_2[a][1]==movie_helper_2[b][1]){
                    k=0;
                }
            }

            return k;
        }
    }







    ///Part 2: Map movies to Users for faster Access (uid : array_movies[])
    static HashMap<Integer,Integer> usr_movies[];
    static void User_Movie_Mapping(){

        ///Part 1: create Array of Map : a map for every user
        usr_movies=new HashMap[944];
        for(int i=0;i<944;i++){
            usr_movies[i]=new HashMap<Integer,Integer>();
        }

        ///Part 2: Traverse the rating list to fill the movies for User
        for(int i=0;i<list_rates.size();i++){
            rate curr=list_rates.get(i);
            int uid=curr.uid;
            int mid=curr.mid;
            usr_movies[uid].put(mid,1);
        }

    }



    static float User_genre_helper[][];//[genre_id][value][frequncy][average_value]
    static void GetMovies(int uid){
        ////Part 1: get a copy of movies sorted based on rating , so that can be sort again
        Integer User_specific_sorted[]= mov_sorted_rating.clone();

        ///Part 2: Highest rated genre based on a specific User
        User_genre_helper=new float[19][3];  //[genre_id][value][frequncy][average_value]


        ////Part 3: Travers list of Rating to fill user_genre_helper : Find movies related to User
        for(int i=0;i<list_rates.size();i++){
            rate curr=list_rates.get(i);

            int rating=curr.rat;
            int mid=curr.mid;

            ////Part 4: Get Genre Related to Movies Seen by User
            if(Movie.containsKey(mid)){

                ArrayList<Integer>movie_generas=Movie.get(mid).gen;


                ///Part 5: Update values of All Generas Related to Specific User
                for(int j=0;j<movie_generas.size();j++){

                    int genera_value=movie_generas.get(j);

                    User_genre_helper[genera_value][0]+=rating;//value
                    User_genre_helper[genera_value][1]++;//freq
                    if(User_genre_helper[genera_value][1]>0){
                        User_genre_helper[genera_value][2]=User_genre_helper[genera_value][0]/User_genre_helper[genera_value][1];//avera_value
                    }
                }
            }


        }

        ////Part 6: Sort movies based on User_genre_helper
        Arrays.sort(User_specific_sorted,new basedOnUser());





        /////////////Part 6:Print 5 movies related to User , which he have not seen and might like
        int counter=0;
        for(int i=User_specific_sorted.length-1;i>=0&& counter<5;i--){
            int val=User_specific_sorted[i];


            if(!usr_movies[uid].containsKey(val)){
                counter++;

                System.out.println(Movie.get(val).title);
            }

        }
        System.out.println();

    }
    static class basedOnUser implements Comparator<Integer>{


        @Override
        public int compare(Integer mid1, Integer mid2) {


            /// val1 :sum of Generas_value related to mid1's genera  and Generas_value value is related to User as taken form User_genre_helper.
            float val1=0;
            if(Movie.containsKey(mid1)){
                ///Generas in which mid1 fall into
                ArrayList<Integer>genreList1=Movie.get(mid1).gen;


                //// Get All Genras Related to a movie
                for(int i=0;i<genreList1.size();i++){

                    /// Get Gernra value for User as taking value form User_genre_helper
                    int G1=genreList1.get(i);
                    /// Update value of val1

                    val1=val1+User_genre_helper[G1][2];
                }
            }

            /// val2 :sum of Generas_value related to mid2's genera  and Generas_value value is related to User as taken form User_genre_helper.
            float val2=0;
            if(Movie.containsKey(mid2)){
                ///Generas in which mid2 fall into
                ArrayList<Integer>genreList2=Movie.get(mid2).gen;


                //// Get All Genras Related to a movie
                for(int i=0;i<genreList2.size();i++){

                    /// Get Gernra value for User as taking value form User_genre_helper
                    int G2=genreList2.get(i);
                    /// Update value of val1

                    val2=val2+User_genre_helper[G2][2];
                }
            }


            //////Sorted based on val1 and val2 ----> natural order
            int k=(val1>val2)?1:-1;
            if(val1==val2){
                k=0;
            }

            return k;
        }
    }


}
