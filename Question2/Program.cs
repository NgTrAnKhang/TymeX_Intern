using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int choice;

            do
            {
                Console.WriteLine("\nMenu:");
                Console.WriteLine("1. Question 2.1");
                Console.WriteLine("2. Question 2.2");
                Console.WriteLine("0. Exit");
                Console.Write("Please select: ");
                choice = Convert.ToInt32(Console.ReadLine());

                switch (choice)
                {
                    case 1:
                        Question2_1 question2_1 = new Question2_1();
                        question2_1.Execute();
                        break;

                    case 2:
                        Question2_2 question2_2 = new Question2_2();
                        question2_2.Execute();
                        break;

                    default:
                        Console.WriteLine("Invalid choice");
                        break;
                }
            } while (choice != 0);
        }
    }
}
