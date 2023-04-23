# Practice_Project_ReadSiteBytes

Small Java project about working with URL and Streams IO

## Task

1. Create a ReadSiteBytes project
2. Create a repository UserRepository with a constructor that accepts a link to a resource as input, initializing a
   field of type String with all the information that the site server will return
3. For the test, take data from the resource https://jsonplaceholder.typicode.com/users
4. Write a toString method
5. Through the class method, find all occurrences of the given string in the string-field of the class, returning them
   as a list.
   An occurrence is the position where a searched string occurs within a given string. The string should represent whole
   object.
   For ex: search="street" -> List<String> = `["Hoeger Mall","Skiles Walks" .. etc]`
   For ex: search="geo" -> List<String> = `[{
   "lat": "-31.8129",
   "lng": "62.5342"
   },{
   "lat": "-71.4197",
   "lng": "71.7478"
   }
... etc]`
    
6. Find for each character }, {, ], [ how many times it occurs in the string-field of the class, returning the result as
   a HashMap<Character, Integer>
