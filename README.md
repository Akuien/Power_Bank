# Power Bank 2
Controllers --> UseCases --> Repositories --> Domain

Controllers: manage the flow of the program in such a way that they able to control the activity of the user.

UseCases: basically referring to the business logic of our program. They will only contain the execute method so that
we can keep them simple (-80 code lines) and understandable avoiding future conflicts.

Repositories: classes in charge of deleting, accessing, adding or modifying the database. These classes inherit
from the AbstractRepository so that the data can persist every time we run an useCase.

Domain: all the tangible things that conform our program. They describe the foundations where the rest of the program
may be built.
<br>-Exceptions
<br>-Constants: repetitive values all over the program (we use OOP so we need objects for that).
<br>-Entities: "our universe"
