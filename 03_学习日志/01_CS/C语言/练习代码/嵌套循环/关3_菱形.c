/* 第 3 关 · 菱形（进阶）
   输入奇数 n，输出 n 行菱形：上半先打空格再打星号，下半倒着算
   ↓ 你的代码写在这里 ↓
*/
#include <stdio.h>

int main(void)
{

    int n;
    scanf("%d", &n);

    int m = (n + 1) / 2;
    for (int k = 1; k <= m; k++)
    {
        int space = m - k;
        int stars = 2 * k - 1;

        for (int s = 1; s <= space; s++)
        {
            printf(" ");
        }
        for (int t = 1; t <= stars; t++)
        {
            printf("*");
        }
        printf("\n");


    }

    for (int k = m - 1; k >= 1; k--)
    {
        int space = m - k;
        int stars = 2 * k - 1;
        for (int s = 1; s <= space; s++)
        {
            printf(" ");
        }
        for (int t = 1; t <= stars; t++)
        {
            printf("*");
        }
        printf("\n");
    }
    return 0;
}
