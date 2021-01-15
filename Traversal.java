import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Traversal {
    static class user{
        int uid;
        int age;
        char gender;
        String job;
        String zip;
        user(int u,int a,char g,String j,String t){
            uid=u;
            age=a;
            gender=g;
            job=j;
            zip=t;
        }

    }
    static class rate{
        int uid;
        int mid;
        int rat;
        int time;

        rate(int u,int m,int r,int t){
            uid=u;
            mid=m;
            rat=r;
            time=t;
        }

    }
    static class movie{

        int mid;
        String title;
        String rdate;
        String vdate;
        String url;
        ArrayList<Integer> gen;
        movie(int m,String t,String r,String v,String u){
            mid=m;
            title=t;
            rdate=r;
            vdate=v;
            url=u;
            gen=new ArrayList<Integer>();
        }

    }
    static HashMap<Integer,String> Genre;///gid gname
    static HashMap<Integer,user> Users;///uid (uid age gender job zip)
    static HashMap<Integer,movie> Movie;///mid (mid title rate vdate url 19gen)

    static ArrayList<rate>list_rates;



    static void getMovie( String path_with_file_name) throws FileNotFoundException {
        if((path_with_file_name.compareTo("path")==0)){
            path_with_file_name="C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\movie.data";

        }
       // path_with_file_name="C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\movie.data";
        File file1=new File(path_with_file_name);//open file
        Scanner scan1=new Scanner (file1);//scan file
        Movie=new HashMap<>();

        ///////////////

        while(scan1.hasNextLine()){

            String curr_str=scan1.nextLine();
            String arr1[]=curr_str.split("\\|");//split at every |
            String title="";
            String rdate="";
            String vdate="";
            String url="";
            int mid=0;

            if(arr1.length>23){

                mid=Integer.parseInt(arr1[0]);
                title=arr1[1];
                rdate=arr1[2];
                vdate=arr1[3];
                url=arr1[4];
                movie curr_movie=new movie(mid,title,rdate,vdate,url);
                for(int i=5;i<arr1.length;i++){
                    if(arr1[i].length()>0 && arr1[i].charAt(0)=='1'){
                        curr_movie.gen.add(i-5);

                    }
                }
                Movie.put(mid,curr_movie);

            }

        }


    }


    /////////////////part -2
    static void getRating(String path_with_file_name) throws FileNotFoundException {
        if((path_with_file_name.compareTo("path")==0)){
            path_with_file_name="C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\ratings.data";

        }
      //  path_with_file_name="C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\ratings.data";
        File file1=new File(path_with_file_name);//open file
        Scanner scan1=new Scanner (file1);//scan file
        list_rates=new ArrayList<rate>();

        ///////////////
        while(scan1.hasNextLine()){
            String arr1[]=scan1.nextLine().split("\\t");//split at every |

            if(arr1.length>1){
                int uid=Integer.parseInt(arr1[0]);
                int mid=Integer.parseInt(arr1[1]);
                int rat=Integer.parseInt(arr1[2]);
                int time=Integer.parseInt(arr1[3]);
                list_rates.add(new rate(uid,mid,rat,time));

            }

        }

    }


    /////part 3

    static void getUser(String path_with_file_name) throws FileNotFoundException {
        path_with_file_name="C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\user.data";
        File file1=new File(path_with_file_name);//open file
        Scanner scan1=new Scanner (file1);//scan file
        Users=new HashMap<>();

        while(scan1.hasNextLine()){
            String arr1[]=scan1.nextLine().split("\\|");//split at every |

            if(arr1.length>3){
                int uid=Integer.parseInt(arr1[0]);
                int age=Integer.parseInt(arr1[1]);
                char gender=arr1[2].charAt(0);
                String job=arr1[3];
                String time=arr1[4];
                Users.put(uid,new user(uid,age,gender,job,time));

            }

        }

    }
    static void  getGenera(String path_with_file_name) throws FileNotFoundException {
        if((path_with_file_name.compareTo("path")==0)){
            path_with_file_name="C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\genre.data";

        }
       // path_with_file_name="C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\genre.data";
        File file1=new File(path_with_file_name);//open file
        Scanner scan1=new Scanner (file1);//scan file
        Genre=new HashMap<Integer,String>();//hashMap


        ///////////////
        while(scan1.hasNextLine()){
            String arr1[]=scan1.nextLine().split("\\|");//split at every |

            if(arr1.length>1){
                int val=Integer.parseInt(arr1[1]);
                String str=arr1[0];
               Genre.put(val,str);
            }

        }


    }




}
