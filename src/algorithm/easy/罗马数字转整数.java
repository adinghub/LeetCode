package algorithm.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Qidong Ding
 * @description TODO： 13. 罗马数字转整数
 * @date 2022-03-04-10:32
 * @since JDK 1.8
 *
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class 罗马数字转整数 {
    public static void main(String[] args) {
        System.out.println(new 罗马数字转整数.Solution().romanToIntSwitch("IX"));
    }

    static class Solution {

        /**
         * 使用 HashMap
         * @param s
         * @return
         */
        public int romanToIntMap(String s) {
            // 先将 所有的基本和特殊规则放入 map
            Map<String, Integer> map = new HashMap<>();
            map.put("I", 1);
            map.put("IV", 4);
            map.put("V", 5);
            map.put("IX", 9);
            map.put("X", 10);
            map.put("XL", 40);
            map.put("L", 50);
            map.put("XC", 90);
            map.put("C", 100);
            map.put("CD", 400);
            map.put("D", 500);
            map.put("CM", 900);
            map.put("M", 1000);

            // 定义一个 sum 变量 累加 整数
            int sum = 0;
            // 遍历 map ，为了减少循环次数，两位的字符的优先级高于一位字符
            for (int i = 0; i < s.length(); ) {
                // 判断 是否为 两位字符
                if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                    sum += map.get(s.substring(i, i + 2));
                    // 将 i 往后移2位
                    i+=2;
                }else {  // 判断 一位数
                    sum += map.get(s.substring(i, i + 1));
                    // 将 i 往后 移一位
                    i++;
                }
            }
            return sum;
        }

        /**
         * 使用 Switch
         * @param s
         * @return
         */
        public int romanToIntSwitch(String s) {
            int sum = 0;
            int preNum = getValue(s.charAt(0));
            for(int i = 1;i < s.length(); i ++) {
                int num = getValue(s.charAt(i));
                if(preNum < num) {
                    sum -= preNum;
                } else {
                    sum += preNum;
                }
                preNum = num;
            }
            sum += preNum;
            return sum;
        }

        public int getValue(char ch) {
            switch(ch) {
                case 'I': return 1;
                case 'V': return 5;
                case 'X': return 10;
                case 'L': return 50;
                case 'C': return 100;
                case 'D': return 500;
                case 'M': return 1000;
                case 'a': return 4;
                case 'b': return 9;
                case 'c': return 40;
                case 'd': return 90;
                case 'e': return 400;
                case 'f': return 900;
            }
            return 0;
        }
    }
}
