/* 第 1 关 · 直角三角形
   输入正整数 n（1<=n<=20），输出 n 行，第 i 行有 i 个 *
   样例 n=4 ->  * / ** / *** / ****
   脚手架：外层 i 管行，内层 j 管这一行打几个，外层末尾换行
   ↓ 你的代码写在这里 ↓
*/
#include <stdio.h>

int main(void)
{

    int n;
    scanf("%d", &n);

    for (int i = 1; i <= n; i++)
    {
        for(int j = 1; j <= i; j++)
        {
            printf("*");
        }
        printf("\n");
    }
    return 0;
}
