class Color {
/*	
	public:
	Color(int a = 0);
*/	
/*	
	private:
	 int a;
	 */
};


class Cat
{
  public:
    Cat() : m_age(5), m_color()
    {
    }

    void miau(int times);

  private:
    unsigned int m_age;
    Color m_color;
};