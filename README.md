# My-Daily-LeetCoding
LeetCode做题记录，持续锻炼思维能力

# 2024-04-02

## 1.跳跃游戏(中等)

### 1.1 逆推，借用一个新数组(2024-04-02)
用时超过8.63%，消耗内存超过58.54%

逆向遍历nums，借助一个等长的新数组，记录每个位置是否能达到最后的位置，如果能，只直接将该位置置为true。如果不能直接到达，那就判断这个位置是否能到下一个为true的位置，因为true就表示这个位置是最终能到达最后一个位置的。如果仍然不能达到下一个true，那么该位置就置为false，标识该位置无论如何都无法到达最后。遍历结束后，判断temp[0]是否是true，true就表示最终能到最后的位置。

### 1.2 逆推，将数组换成map集合(2024-04-02)
用时超过5.01%，消耗内存超过31.08%

逆向遍历nums，换成map后，理论上在第二步判断该位置能否跳到下一个true的位置时，查询效率应该更高，O(1)，数组是O(n)。但是时间更长的原因，预测应该是元素太多，map集合出现了大量的resize，导致时间更长了。



### 1.3 逆推，只需要借助一个int变量(2024-04-02)
用时超过93.43%，消耗内存超过90.52%

逆向遍历nums。其实是优化了方案1和2。方案1中是逆推时判断当前位置是否能找到下一个true作为跳板，然后会去遍历临时数组。但是实际这个地方可以优化，假如当前下标是3，已知临时数组中的5,8位置都是true，现在我只需要知道离当前位置最近的一个跳板即可，即我每次只需要记录最小位置的一个跳板下标即可



# 2024-03-26

## 1.连续销售最高总额(简单)

### 1.1 最佳方案(2024-03-26)
用时超过，消耗内存超过



## 2.两数之和(简单)

### 2.1 暴力破解(2024-03-26 12)
用时超过，消耗内存超过

用两层循环去处理，时间复杂度O(n^2)

### 2.2 一层循环解决(2024-03-26 20)
用时超过，消耗内存超过

已经知道两数之和为target，每次遍历的时候把当前值和序号存入map，如果遍历的时候发现target-当前值在map中存在，则直接返回



# 2024-03-27

## 1. 买卖股票的最佳时机 II(简单)



# 2024-03-29

## 1.删除有序数组中的重复项 II(中等)

### 1.1 循环，O(n)，借用临时数组来实现(2024-03-29)
用时超过100%，消耗内存超过57.81%

因为数组是有序的，所以循环判断的时候，记录下当前的数，然后往后遍历，如果一致，则count++，如果发现count超过2，则说明这个数不应该放到新数组里，则跳过。最后将新数组的数据拷贝到nums中即可。该方法只需要遍历一次，时间复杂度较低，但是借用了一个临时数组，所以内存占用稍微高一点

## 2.轮转数组(中等)

### 2.1 取余，借助新数组(2024-03-29)
用时超过61.76%，消耗内存超过83.97%

取余后确认最小移动的位数，然后循环将每个数+k后确认它新的位置，放到新的数组中

### 2.2 取余，借助System.arraycopy方法，减少代码量(2024-03-29)
用时超过100%，消耗内存超过67.83%

本质上思路还是跟第一个差不多，区别在于是将移动会后超过数组最右边的数据放到新数组中，然后再将不会超过最右边的数据移动到新数组的最后



