package org.rivalry.swingui;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang.StringUtils;

/**
 * Provides a user interface look and feel.
 */
public class LookAndFeel implements Comparable<LookAndFeel>
{
    /** Available look and feels. */
    private static final Set<LookAndFeel> LOOK_AND_FEELS = new TreeSet<LookAndFeel>();

    static
    {
        for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        {
            final String name = info.getName();
            final String lafClassName = info.getClassName();
            boolean isCrossPlatform = true;

            if (name.contains("Mac OS") || name.contains("Windows"))
            {
                isCrossPlatform = false;
            }

            final LookAndFeel laf = new LookAndFeel(name, lafClassName,
                    isCrossPlatform);

            LOOK_AND_FEELS.add(laf);
        }
    }

    /**
     * @param name Name.
     * 
     * @return the look and feel with the given name.
     */
    public static LookAndFeel findByName(final String name)
    {
        LookAndFeel answer = null;

        if (StringUtils.isNotEmpty(name))
        {
            final Iterator<LookAndFeel> iter = LOOK_AND_FEELS.iterator();

            while ((answer == null) && iter.hasNext())
            {
                final LookAndFeel laf = iter.next();

                if (name.equals(laf.getName()))
                {
                    answer = laf;
                }
            }
        }

        return answer;
    }

    /**
     * @return the available look and feels.
     */
    public static final Set<LookAndFeel> getAvailableLookAndFeels()
    {
        return LOOK_AND_FEELS;
    }

    /**
     * @return the default look and feel.
     */
    public static final LookAndFeel getDefaultLookAndFeel()
    {
        LookAndFeel answer = findByName("Metal");

        if (answer == null)
        {
            answer = findByName("Nimbus");
        }

        return answer;
    }

    /** Name. */
    private final String _name;

    /** Class name. */
    private final String _className;

    /** Flag indicating if this is cross platform. */
    private final boolean _isCrossPlatform;

    /**
     * Construct this object with the given parameters.
     * 
     * @param name Name.
     * @param className Class name.
     * @param isCrossPlatform Flag indicating if this is cross platform.
     */
    public LookAndFeel(final String name, final String className,
            final boolean isCrossPlatform)
    {
        _name = name;
        _className = className;
        _isCrossPlatform = isCrossPlatform;
    }

    /**
     * Apply this look and feel.
     */
    public void apply()
    {
        try
        {
            UIManager.setLookAndFeel(getClassName());
        }
        catch (final ClassNotFoundException e)
        {
            handleException(e);
        }
        catch (final InstantiationException e)
        {
            handleException(e);
        }
        catch (final IllegalAccessException e)
        {
            handleException(e);
        }
        catch (final UnsupportedLookAndFeelException e)
        {
            handleException(e);
        }
    }

    @Override
    public int compareTo(final LookAndFeel another)
    {
        int answer = 0;

        if (another == null)
        {
            answer = -1;
        }
        else
        {
            answer = getName().compareTo(another.getName());
        }

        return answer;
    }

    /**
     * @return the className
     */
    public String getClassName()
    {
        return _className;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * @return the isCrossPlatform
     */
    public boolean isCrossPlatform()
    {
        return _isCrossPlatform;
    }

    /**
     * @param e Exception.
     */
    private void handleException(final Exception e)
    {
        System.out.println(e.getClass().getName() + ": " + e.getMessage());
    }
}
