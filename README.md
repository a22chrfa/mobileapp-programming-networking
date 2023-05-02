# Rapport

Detta är den 4:e forken som gjorts. Uppgiften var krånglig att få till att jag fick börja om flera gånger.
I de tidigare forks gjordes commits men när inget fungerade gjordes många små ändringar överallt vilket gjorde
att commits glömdes bort, men också att vettiga commits vara svåra att skriva.

Denna fork är således en kombination av fungerande kod från mina tidigare forks, tillsammans med den slutgiltiga koden.

Först skapades en recyclerview i activity main, därefter skapades medlemmen "ArrayList<Mountain> mountains" med tillhörande klass.
Därefter skapades en layout för list_mountains som beskrevs i canvas.

Efter detta gavs appen permission till internet och recyclerViewAdapter skapades.

För att förstå var datan lagras (json):
(https://developer.android.com/reference/android/os/AsyncTask)

För att förstå hur adapter ska sättas upp.
(https://developer.android.com/develop/ui/views/layout/recyclerview#java)
(https://guides.codepath.com/android/using-the-recyclerview)
(canvas)

Adapter sattes upp enligt den sista länken med en intern viewholder.

```
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Mountain> mountains;

    public RecyclerViewAdapter(List<Mountain> mountains) {
        this.mountains = mountains;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        public TextView name;



        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {

            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            name = itemView.findViewById(R.id.title);

        }
    }

```

Enligt tips från uppgiften kördes sedan toString i mountain-klassen.

I main används nedan kod för att köra jsonTask på url:en och att adaptern ska populera i recyclerViewID.

```
        new JsonTask(this).execute(JSON_URL);

        // Lookup the recyclerview in activity layout
        recyclerView = findViewById(R.id.recyclerViewID);
        mountains = new ArrayList<Mountain>();
        
        // Create adapter passing in the sample user data
        mountainAdapter = new RecyclerViewAdapter(mountains);
        
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(mountainAdapter);
        
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
```

i onPostExecute körs följande för att parsa json
(https://www.javacodeexamples.com/gson-convert-json-to-a-typed-arraylist/14925)

```
ArrayList<Mountain> data = new Gson().fromJson(json, new TypeToken<ArrayList<Mountain>>(){}.getType());
mountains.addAll(data);
```

Screenshot där namnen presenteras.
![mountains_screen](Screen_mountains.png)