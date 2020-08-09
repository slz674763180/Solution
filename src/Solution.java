import javax.swing.text.html.parser.Entity;
import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {


    static class Codec {

        // Encodes a tree to a single string.

        public String serialize(TreeNode root) {
            StringBuilder ans = new StringBuilder("[");
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode temp = queue.poll();
                if (temp.val == 123321) {
                    if (!queue.isEmpty()) {
                        ans.append("null,");
                        continue;
                    } else {
                        break;
                    }
                }
                ans.append(temp.val).append(",");
                if (temp.left != null) {
                    queue.add(temp.left);
                } else {
                    queue.add(new TreeNode(123321));
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                } else {
                    queue.add(new TreeNode(123321));
                }
            }
            int index = 0;
            for (int j = ans.length() - 1; j >= 0; j--) {
                if (ans.charAt(j) >= '0' && ans.charAt(j) <= '9') {
                    index = j;
                    break;
                }
            }
            return ans.substring(0, index + 1) + "]";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            data = data.substring(1, data.length() - 1);
            String[] res = data.split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(res[0]));
            queue.add(root);
            for (int i = 1; i < res.length; i += 2) {
                String left = res[i];
                TreeNode tmp = queue.poll();
                if (!left.equals("null")) {
                    TreeNode newletf = new TreeNode(Integer.parseInt(left));
                    tmp.left = newletf;
                    queue.add(newletf);
                }
                if (i < res.length - 1) {
                    String right = res[i + 1];
                    if (!right.equals("null")) {
                        TreeNode newright = new TreeNode(Integer.parseInt(right));
                        tmp.right = newright;
                        queue.add(newright);
                    }
                }
            }
            return root;
        }
    }


    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length < 3) return true;
        return verifyPostorder1(postorder, 0, postorder.length - 1);
    }

    public boolean verifyPostorder1(int[] postorder, int start, int end) {
        if (end - start < 3) return true;
        int root = postorder[end];
        boolean flag = false;
        int index = 0;
        for (int i = start; i < end; i++) {
            if (flag && postorder[i] < root) {
                return false;
            }
            if (!flag && postorder[i] > root) {
                index = i;
                flag = true;
            }
        }
        if (index == 0) index = end;
//        if(index==start)end--;
        if (!verifyPostorder1(postorder, start, index - 1)) return false;
        return verifyPostorder1(postorder, index, end - 1);
    }

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return ans;
        List<Integer> curr = new LinkedList<>();
        pathSum1(root, sum, curr);
        return ans;
    }

    public void pathSum1(TreeNode root, int sum, List<Integer> curr) {
//        List<Integer> curr1 = new ArrayList(curr);
        int val = root.val;
        sum -= val;
        curr.add(val);
        if (sum == 0) {
            if (root.left == null && root.right == null) {
                ans.add(new ArrayList<>(curr));
            }
        }
        if (root.left != null) {
            pathSum1(root.left, sum, curr);
        }
        if (root.right != null) {
            pathSum1(root.right, sum, curr);
        }
        curr.remove(curr.size() - 1);
    }

    //    public Node copyRandomList(Node head) {
//        if(head==null)return null;
//        Node p=head;
//        Node q=new Node(head.val);
//        Node l=q;
//        while (p.next!=null){
//            p=p.next;
//            Node temp = new Node(p.val);
//            l.next=temp;
//            l=temp;
//        }
//        p=head;
//        l=q;
//        Node m=q;
//        Node o=head;
//        while (p!=null){
//            Node temp = p.random;
//            while (temp!=null&&o!=null){
//                if(o.equals(temp)){
//                    m.random=l;
//                    break;
//                }
//                l=l.next;
//                o=o.next;
//            }
//            l=q;
//            o=head;
//            m=m.next;
//            p=p.next;
//        }
//        return q;
//    }
//    public Node copyRandomList(Node head) {
//        Node p=head;
//        HashMap<Node,Node> hashMap=new HashMap<>();
//        while (p!=null){
//            hashMap.put(p,new Node(p.val));
//            p=p.next;
//        }
//        p=head;
//        Node res = hashMap.get(p);
//        while (p!=null){
//            hashMap.get(p).next=hashMap.get(p.next);
//            hashMap.get(p).random=hashMap.get(p.random);
//            p=p.next;
//        }
//        return res;
//    }
    Queue<Node> queue = new ArrayDeque<>();

    public Node treeToDoublyList(Node root) {
        treeToDoublyList1(root);
        Node head = queue.poll();
        Node temp = head;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            temp.right = curr;
            curr.left = temp;
            temp = curr;
        }
        head.left = temp;
        temp.right = head;
        return head;
    }

    public void treeToDoublyList1(Node root) {
        if (root == null) return;
        treeToDoublyList1(root.left);
        queue.add(root);
        treeToDoublyList1(root.right);
    }

    Set<String> res = new LinkedHashSet<>();

    public String[] permutation(String s) {
        permutation1(s, "");
        return (String[]) res.toArray(new String[0]);
    }

    public void permutation1(String rest, String curr) {
        if (rest.isEmpty()) {
            res.add(curr);
        }
        for (int i = 0; i < rest.length(); i++) {
            curr += rest.charAt(i);
            permutation1(rest.substring(0, i) + rest.substring(i + 1), curr);
            curr = curr.substring(0, curr.length() - 1);
        }
    }

    public int majorityElement(int[] nums) {
        int ans = 0;
        int num = 0;
        for (int val : nums) {
            if (num == 0) {
                ans = val;
                num++;
            } else if (ans == val) {
                num++;
            } else {
                num--;
            }
        }
        return ans;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[k];
        for (int i = 0; i < arr.length; i++) {

        }
        return ans;
    }

    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : nums) {
            sum += val;
            ans = sum > ans ? sum : ans;
            if (sum < 0) sum = 0;
        }
        return ans;
    }

    public int countDigitOne(int n) {
        String str = n + "";
        int size = str.length();
        int ans = 0;
        for (int i = size - 1; i >= 0; i--) {
            int pow = (int) Math.pow(10, i);
            int chu = Integer.parseInt(str.charAt(size - i - 1) + "");
            int yu = 0;
            if (i != 0) {
                yu = Integer.parseInt(str.substring(size - i));
            }
            if (chu > 1) {
                ans += Math.pow(10, i);
            } else if (chu > 0) {
                ans += yu + 1;
            }
            if (i != size - 1) {
                ans += pow * Integer.parseInt(str.substring(0, size - i - 1));
            }
        }
        return ans;
    }

    public int findNthDigit(int n) {
        if (n < 10) return n;
        long[] res = {10, 190, 2890, 38890, 488890, 5888890, 68888890, 788888890};
        int index = 0;
        for (int i = 1; i < 8; i++) {
            if (n < res[i]) {
                index = i;
                break;
            }
            if (i == 7) index = 8;
        }
        n -= res[index - 1];
        int start = (int) Math.pow(10, index) + n / (index + 1) - 1;
        String str = (start + 1) + "";
        return Integer.parseInt(str.charAt(n % (index + 1)) + "");
    }

    public String minNumber(int[] nums) {
        for(int i=0;i<nums.length;i++ ){
            for(int j=i+1;j<nums.length;j++){
                if(sort(nums[i],nums[j])){
                    int temp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=temp;
                }
            }
        }
        StringBuilder ans= new StringBuilder();
        for (int num : nums) {
            ans.append(num);
        }
        return ans.toString();
    }
    boolean sort(int a,int b){
        String left = a+""+b;
        String right = b+""+a;
        for(int h=0;h<left.length();h++){
            if(left.charAt(h)==right.charAt(h))continue;
            return left.charAt(h)>right.charAt(h);
        }
        return false;
    }
    int ans1=0;
    public int translateNum(int num) {
        String str = String.valueOf(num);
        translateNum1(str);
        return ans1;
    }
    public void translateNum1(String str) {
        if(str.length()<=1) {
            ans1++;
            return;
        }
        if(str.charAt(0)=='1'||str.charAt(0)=='2'&&str.charAt(1)<'6'){
            translateNum1(str.substring(2));
        }
        translateNum1(str.substring(1));
    }
    public int maxValue(int[][] grid) {
        int n= grid.length,m=grid[0].length;
        int[][] isvisit = new int[n][m];
        for(int i=0;i<n;i++) for(int j=0;j<m;j++)maxValue1(grid,i,j,isvisit);
        return isvisit[n-1][m-1];
    }
    public void maxValue1(int[][] grid,int x,int y,int[][] isvisit){
        if(x==0&&y==0)isvisit[0][0]=grid[0][0];
        else if(x==0)isvisit[x][y]=grid[x][y]+isvisit[x][y-1];
        else if(y==0)isvisit[x][y]=grid[x][y]+isvisit[x-1][y];
        else isvisit[x][y]=grid[x][y]+Math.max(isvisit[x-1][y],isvisit[x][y-1]);
    }
    public int lengthOfLongestSubstring(String s) {
        String str= "";
        int ans=0;
        int index;
        for(int i=0;i<s.length();i++){
            index = str.indexOf(s.charAt(i)+"");
            str += s.charAt(i);
            if(index==-1){
                ans = Math.max(ans,str.length());
            }else{
                str = str.substring(index);
            }
        }
        return ans;
    }

    public int nthUglyNumber(int n) {
        List<Integer> list=new LinkedList<>();
        for(int i=1;i>0;i++){
            if(i<7){
                list.add(i);
                continue;
            }
            for(int j=list.size()-1;j>=0;j--){
                if(i/list.get(j)>5)break;
                if(i %list.get(j)==0){
                    if(i /list.get(j)==2|| i /list.get(j)==3|| i /list.get(j)==5){
                        list.add(i);
                    }
                    break;
                }
            }
            if(list.size()>=n)return list.get(n-1);
        }
        return 0;
    }

    public char firstUniqChar(String s) {

        for(int i=0;i<s.length();i++){
            if(s.indexOf(s.charAt(i)+"")==s.lastIndexOf(s.charAt(i)+""))return s.charAt(i);
        }
        return ' ';
    }

    int res1=0;
    public int reversePairs(int[] nums) {
        reversePairs1(nums);
        return res1;
    }

    int[] reversePairs1(int[] nums){
        if(nums.length==1){
            return nums;
        }
        int n=nums.length/2;
        int[] left = new int[n];
        int[] right = new int[nums.length-n];
        System.arraycopy(nums,0,left,0,n);
        System.arraycopy(nums,n,right,0,nums.length-n);
        left = reversePairs1(left);
        right = reversePairs1(right);
        int left_=0,right_=0,index=0;
        while (left_<n&&right_<nums.length-n){
            if(left[left_]<=right[right_]){
                nums[index++]=left[left_];
                left_++;
            }
            else{
                nums[index++]=right[right_];
                right_++;
                res1+=n-left_;
            }
        }
        if(left_==n){
            while (index<nums.length){
                nums[index++]=right[right_++];
            }
        }
        else if(right_==nums.length-n){
            while (index<nums.length){
                nums[index++]=left[left_++];
            }
        }
        return nums;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p=headA;
        ListNode q=headB;
        while (headA!=headB){
            if(headA==null) headA=q;
            if(headB==null)headB=p;
            headA=headA.next;
            headB=headB.next;
        }
        return headA;
    }

    public int search(int[] nums, int target) {
        return search1(nums,target, 0, nums.length);
    }
    int search1(int[] nums, int target, int start, int end){
        if(end<start)return 0;
        int mid = (start+end)/2;
        if(nums[mid] == target){
            int ans=1;
            int left = mid-1, right = mid+1;
            while (nums[left--]==target)ans++;
            while (nums[right++]==target)ans++;
            return ans;
        }
        else if(nums[mid]<target){
            return search1(nums,target,mid+1,end);
        }else{
            return search1(nums,target,start,mid-1);
        }
    }

    public int missingNumber(int[] nums) {
        return missingNumber1(nums);
    }

    int missingNumber1(int[] nums){
        int start=0;
        int end=nums.length-1;
        while (end>start){
            int mid=(start+end)/2;
            if(nums[mid]>mid){
                end=mid;
            }else{
                start=mid+1;
            }
        }
        return start-1;
    }
    List<Integer> anss=new LinkedList<>();
    public int kthLargest(TreeNode root, int k) {
        kthLargest1(root,k);
        return anss.get(anss.size()-1);
    }
    void kthLargest1(TreeNode root,int k){
        if(root==null||anss.size()==k)return;
        kthLargest1(root.left,k);
        anss.add(root.val);
        kthLargest1(root.right,k);
    }

    public int maxDepth(TreeNode root) {
        if(root==null)return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    boolean ansss=true;
    public boolean isBalanced(TreeNode root) {
        isBalanced1(root);
        return ansss;
    }
    int isBalanced1(TreeNode root){
        if(root==null||!ansss)return 0;
        int left = isBalanced1(root.left);
        int right = isBalanced1(root.right);
        if(Math.abs(left-right)>1)ansss=false;
        return Math.max(left,right)+1;
    }

    public int[] singleNumbers(int[] nums) {
        int[] ans=new int[2];
        int sum=nums[0];
        for(int i=1;i<nums.length;i++){
            sum ^= nums[i];
        }
        int index=1;
        for(;index<100;index++){
            int one = (sum>>>index);
            if(one%2==1)break;
        }
        for(int val:nums){
            if((val>>index)%2==0)ans[0]^=val;
            else ans[1]^=val;
        }
        return ans;
    }
    public int singleNumber(int[] nums) {
        int[] ans=new int[10];
        int index;
        for(int val:nums){
            index=0;
            while (val>0){
                ans[index++]+=val%2;
                val=val>>1;
            }
        }
        int res=0;
        for(int i=0;i<10;i++){
            res += (ans[i]%3)*Math.pow(2,i);
        }
        return res;
    }

    public int[][] findContinuousSequence(int target) {
        int[][] ans =new int[100][];
        int index=0;
        int length=(int)Math.sqrt(2*target);
        while (length>1){
            if(length%2==1){
                if(target%length==0){
                    int start = target/length -(length-1)/2;
                    if(create(start,length)!=null)
                        ans[index++]=create(start,length);
                }
            }
            else{
                int leng=length/2;
                if(target%leng==0&&(target/leng)%2==1){
                    int start = (target/leng -1)/2-(leng-1);
                    if(create(start,length)!=null)
                        ans[index++]=create(start,length);
                }
            }
            length--;
        }
        int[][] res = new int[index][];
        if (index >= 0) System.arraycopy(ans, 0, res, 0, index);
        return res;
    }

    int[] create(int start,int length){
        if(start>0){
            int[] temp=new int[length];
            for(int i=start;i<start+length;i++){
                temp[i-start]=i;
            }
            return temp;
        }
        else return null;
    }

    public int lastRemaining(int n, int m) {
        List<Integer> list=new LinkedList<>();
        for(int i=0;i<n;i++){
            list.add(i);
        }
        int yu=0;
        while (list.size()>1){
            yu = (yu+m)%list.size();
            yu--;
            if(yu<0)yu=list.size()-1;
            list.remove(yu);
        }
        return list.get(0);
    }

    public double[] twoSum(int n) {
        double[] ans = new double[n*6-(n-1)];
        double fenmu = Math.pow(6, n);
        int[] tongji=new int[6*n+1];
        int[] pre=new int[6*n+1];
        for(int i=1;i<7;i++){
            tongji[i]=1;
        }
        int m=1;
        while (m<n){
            for(int i=1;i<7;i++){
                for(int j=m;j<=6*m;j++){
                    tongji[j+i]++;
                }
            }
            for(int i=m;i<=6*m;i++){
                if(tongji[i]!=0)
                    tongji[i]-=pre[i];
            }
            System.arraycopy(tongji,0,pre,0,tongji.length);
            m++;
        }
        for(int i=n;i<=6*n;i++){
            ans[i-n]=tongji[i]/fenmu;
        }
        return ans;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        int left=0;
        int right=nums.length-1;
        while (left<right){
            if(nums[left]+nums[right]==target){
                ans[0]=left;
                ans[1]=right;
                break;
            }
            else if(nums[left]+nums[right]>target){
                right--;
            }else left++;
        }
        return ans;
    }

    public String reverseWords(String s) {
        String[] strings=s.split(" ");
        StringBuilder ans= new StringBuilder();
        for(int i=strings.length-1;i>=0;i--){
            if(!strings[i].trim().equals("")){
                ans.append(strings[i]).append(" ");
            }
        }
        return ans.toString().trim();
//        List<String> ans=new LinkedList<>();
//        s=s.trim();
//        StringBuilder temp= new StringBuilder();
//        for(int i=0;i<s.length();i++){
//            if(s.charAt(i)==' '){
//                if(!temp.toString().equals("")){
//                    ans.add(temp.toString());
//                    temp = new StringBuilder();
//                }
//            }else
//                temp.append(s.charAt(i));
//        }
//        ans.add(temp.toString());
//        StringBuilder res= new StringBuilder();
//        for(int i=ans.size()-1;i>=0;i--){
//            res.append(ans.get(i)).append(' ');
//        }
//        return res.toString().trim();
    }

    public String reverseLeftWords(String s, int n) {
        return s.substring(n)+s.substring(0,n);
    }

    public static void main(String[] args) {
        Solution A = new Solution();
        A.reverseWords("the sky is blue");
    }

}

class MedianFinder {
    PriorityQueue<Integer> big = new PriorityQueue<>((n1, n2) -> n2 - n1);
    PriorityQueue<Integer> small = new PriorityQueue<>();
    int size = 0;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {

    }

    public void addNum(int num) {
        big.add(num);
        small.add(big.poll());
        if (small.size() - 1 > big.size()) {
            big.add(small.poll());
        }
    }

    public double findMedian() {
        if (big.isEmpty()) return small.peek();
        if (small.size() == big.size()) {
            return (double) (small.peek() + big.peek()) / 2;
        } else return small.peek();
    }
}

class SoWhat {
    volatile int i = 0; // volatile修饰的变量

    public int getI() {
        return i;// 单个volatile变量的读
    }

    public void setI(int j) {
        this.i = j; // 单个volatile 变量的写
    }

    public void inc() {
        i++;//复合多个volatile 变量
    }
}

class SynSoWhat {
    int i = 0;

    public synchronized int getI() {
        return i;
    }

    public synchronized void setI(int j) {
        this.i = j;
    }

    public void inc() { // 普通方法调用
        int tmp = getI(); // 调用已同步方法
        tmp = tmp + 1;//普通写方法
        setI(tmp);// 调用已同步方法
    }
}


class Nod1e {
    int val;
    Node next;
    Node random;

    public Nod1e(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}