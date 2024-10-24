using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test
{
    internal class Question2_1
    {
        public void Execute()
        {
            List<Product> products = new List<Product>();
            products.Add(new Product("Laptop", 999.99, 5));
            products.Add(new Product("Smartphone", 499.99, 10));
            products.Add(new Product("Tablet", 299.99, 0));
            products.Add(new Product("Smartwatch", 199.99, 3));
            
            Console.WriteLine();
            Console.WriteLine("Product List");
            foreach (Product p in products)
            {
                Console.WriteLine($"{p.Name}:price {p.Price},quantity {p.Quantity}");
            }
            Console.WriteLine();
            double total = caculateTotal(products);
            Console.WriteLine($"Inventory Total: {total.ToString("N2")}");
            Console.WriteLine();
            string mostvalue = MostExpensive(products);
            Console.WriteLine($"Most Expensive Product: {mostvalue.ToString()}");
            Console.WriteLine();
            Console.WriteLine("Find Product");
            Console.Write("Please write the name of the product: ");
            string item = Console.ReadLine();
            Console.WriteLine(FindProduct(products, item));
            Console.WriteLine();
            Console.WriteLine("Price Descending");
            products = SortByPrice(products);
            Print(products);
            Console.WriteLine();
            Console.WriteLine("Quantity Ascending");
            products=SortByQuantity(products);
            Print(products);
        }
        public void Print(List<Product> lstPro)
        {
            foreach (Product p in lstPro)
            {
                Console.WriteLine($"{p.Name}:price {p.Price},quantity {p.Quantity}");
            }

        }
        public double caculateTotal(List<Product> lstPro)
        {
            double total = 0;
            if (lstPro == null || lstPro.Count == 0)
            {
                return 0;
            }
            else
            {
                foreach (Product p in lstPro)
                {
                    total += p.Price * p.Quantity;
                }
            }
            return total;

        }
        public string MostExpensive(List<Product> lstPro)
        {
            string mostExpensiveProduct="No product";
            double highestPrice = lstPro[0].Price;
            if (lstPro == null || lstPro.Count == 0)
            {
                return "No product";
            }
            else
            {
                foreach (Product p in lstPro)
                {
                    if (p.Price >= highestPrice)
                    {
                        mostExpensiveProduct = p.Name;
                    }
                }
                return mostExpensiveProduct;
            }
        }
        public bool FindProduct(List<Product> lstPro,string item)
        {
            bool result=false;
            if (lstPro == null || lstPro.Count == 0)
            {
                return result;
            }
            else
            {
                foreach (Product p in lstPro)
                {
                    if (p.Name.Equals(item))
                    {
                        result=true;
                    }
                }
                return result;
            }
        }
        public List<Product> SortByPrice(List<Product> lstPro)
        { 
            List<Product> result = new List<Product>(lstPro);
            for (int i = 0; i < result.Count - 1; i++)
            {
                int maxIndex = i;
                for (int j = i + 1; j < result.Count; j++)
                {
                    if (result[j].Price > result[maxIndex].Price)
                    {
                        maxIndex = j;
                    }
                }
                Product temp = result[maxIndex];
                result[maxIndex] = result[i];
                result[i] = temp;
            }
            return result;
        }
        public List<Product> SortByQuantity(List<Product> lstPro)
        {
            List<Product> result = lstPro;
            for (int i = 0; i < lstPro.Count - 1; i++)
            {
                int minIndex = i;
                for (int j = i + 1; j < lstPro.Count; j++)
                {
                    if (lstPro[j].Quantity < lstPro[minIndex].Quantity)
                        minIndex = j;
                }
                Product temp = lstPro[minIndex];
                lstPro[minIndex] = lstPro[i];
                lstPro[i] = temp;
            }
            return result;
        }
    }
}
