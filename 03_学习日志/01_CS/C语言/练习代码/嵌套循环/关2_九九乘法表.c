/* 第 2 关 · 九九乘法表（下三角）
   第 i 行输出 j*i=积（j 从 1 到 i）
   ↓ 你的代码写在这里 ↓
*/
#include <stdio.h>

int main(void)
{

    for (int i = 1; i <= 9; i++)
    {
        for(int j = 1; j <= i; j++)
        {
            printf("%d*%d=%d  ", j, i, i * j);
        }
        printf("\n");
    }
    return 0;
}
