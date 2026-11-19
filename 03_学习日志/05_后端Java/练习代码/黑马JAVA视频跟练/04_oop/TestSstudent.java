public class TestSstudent {
    public static void main(String[] args) {
        Sstudent[] arr = new Sstudent[3];

        Sstudent stu1 = new Sstudent(1, "zhangsan", 16);
        Sstudent stu2 = new Sstudent(2, "xiaohong", 16);
        Sstudent stu3 = new Sstudent(3, "xiaoliang", 17);

        arr[0] = stu1;
        arr[1] = stu2;
        arr[2] = stu3;

        Sstudent sut4 = new Sstudent(4, "jianailiang", 18);

        boolean flag = contains(arr, 4);

        if (flag){
            //这里还要把学生添加到数组当中，但是添加前还要判断数组长度，还要写一个方法
            int count = getCount(arr);
            if (count < arr.length){
                arr[count] = sut4;

                printArr(arr);
            }else{
                Sstudent[] new_arr = creatNewArr(arr);
                new_arr[count] = sut4;
                arr = new_arr;

                printArr(new_arr);
            }
        }else{
            System.out.println("新录入的学生ID重复，请重新录入！！！");
        }
        //上面的这一段代码是增和查，下面我要写删除怎么删除

        int index = indexPosition(arr, 4);

        if (index >= 0){
            arr[index] = null;
            printArr(arr);
        }else{
            System.out.println("数组中没有该元素，删除失败！！！");
        }

        int change = indexPosition(arr, 2);

        if (change >= 0){
            Sstudent stust = arr[change];
            int newAge = stust.getAge() + 1;
            stust.setAge(newAge);

            printArr(arr);
        }else{
            System.out.println("该元素在此数组中不存在，修改失败！！！");
        }


    }

    public static int indexPosition(Sstudent[] arr, int id){
        for (int i = 0; i < arr.length; i++) {
            Sstudent stu = arr[i];

            if (stu != null){
                int sid = stu.getId();
                if (sid == id){
                    return i;
                }
            }//这里的部分我没写好，感觉有点太奇怪，
        }

        return -1;
    }

    public static void printArr(Sstudent[] arr){
        for (int i = 0; i < arr.length; i++) {
            Sstudent temp = arr[i];
            if (temp == null){
                continue;
            }else {
                System.out.println(temp.getId() + "," + temp.getName() + "," + temp.getAge());
            }
        }
    }  //遍历打印

    public static Sstudent[] creatNewArr(Sstudent[] arr){
        Sstudent[] new_arr = new Sstudent[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            new_arr[i] = arr[i];
        }
        return new_arr;
    }  //长度不满足时要创造的新数组方法

    public static int getCount(Sstudent[] arr){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null){
                count++;
            }
        }
        return count;
    }    //这个是拿来统计实际收录元素的方法，方便比较数组有没有填充完

    public static boolean contains(Sstudent[] arr, int id){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null){
                Sstudent stu = arr[i];
                if (stu.getId() == id){
                    return false;
                }
            }
        }

        return true;
    }  //这个方法是看学生的id是否重复

}
