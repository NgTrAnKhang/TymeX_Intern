using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test
{
    internal class Question2_2
    {
        public void Execute()
        {
            Console.WriteLine();
            Console.Write("Please enter size(n):");
            Input(out int n);
            int[] ranNum = new int[n];
            List<int> temp = new List<int>();
            while (temp.Count < n)
            {
                var ran = new Random();
                int newNum = ran.Next(1, n + 2);
                if (!temp.Contains(newNum))
                {
                    temp.Add(newNum);
                }
            }
            ranNum = temp.ToArray();
            Console.WriteLine("Item in array");
            foreach (var i in ranNum) { Console.Write(i + " "); }
            Console.WriteLine();
            for (int i = 1; i <= n + 1; i++)
            {
                if (!ranNum.Contains(i)) { Console.WriteLine($"The missing number is {FindNumber(ranNum)}"); }
            }
        }
        public void Input(out int n)
        {
            n = Int32.Parse(Console.ReadLine());
        }
        public int FindNumber(int[] ranNum)
        {
            int missingNum = 0;
            for (int i = 1; i <= ranNum.Length + 1; i++)
            {
                if (!ranNum.Contains(i)) { missingNum = i; }
            }
            return missingNum;
        }
    }
}
