package tutcoach;

import java.security.Provider;
import java.security.Security;

public class ListProviders
{
    public static void main(String[] arguments)
    {
        Provider[] availableProviders = Security.getProviders();

        int index = 0;
        while (index < availableProviders.length)
        {
            System.out.print(availableProviders[index].getName());
            System.out.print(": ");
            System.out.print(availableProviders[index].getInfo());
            System.out.println();

            index++;
        }
    }
}
