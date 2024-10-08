import android.content.Context
import android.util.Log
import td.info507.bud.storage.utility.Storage
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

abstract class FileStorage<T>(
    private val context: Context,
    private val name: String,
    private val extension: String
) : Storage<T> {
    private val fileName = "storage_$name.$extension"
    private var data = HashMap<Int, T>()
    private var nextID = 1

    init {
        read()
    }

    protected abstract fun create(id: Int, Obj: T): T
    protected abstract fun stringToData(Value: String): HashMap<Int, T>
    protected abstract fun dataToString(data: HashMap<Int, T>): String
    private fun read() {
        try {
            val input = context.openFileInput(fileName)
            // println(context.filesDir) chemin du fichier
            if (input != null) {
                val builder = StringBuilder()
                val br = BufferedReader(InputStreamReader(input))
                var temp = br.readLine()
                while (temp != null) {
                    builder.append(temp)
                    temp = br.readLine()
                }
                input.close()
                data = stringToData(builder.toString())
                nextID = if (data.keys.isEmpty()) 1 else data.keys.max() + 1
            }
        } catch (e: FileNotFoundException) {
            write()
        }
    }

    private fun write() {
        try {
            val output = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            val writer = OutputStreamWriter(output)
            writer.write(dataToString(data))
            writer.close()
            Log.d("FileStorage", "Données écrites avec succès dans le fichier $fileName")
        } catch (e: Exception) {
            Log.e("FileStorage", "Erreur lors de l'écriture dans le fichier", e)
        }
    }

    override fun insert(obj: T): Int {
        data.put(nextID, create(nextID, obj))
        nextID++
        write()
        return nextID - 1
    }

    override fun size(): Int {
        return data.size
    }

    override fun find(id: Int): T? {
        return data[id]
    }

    override fun findAll(): List<T> {
        return data.toList().map { pair -> pair.second }
    }

    override fun update(id: Int, obj: T) {
        data.put(id, obj)
        write()
    }

    override fun delete(id: Int) {
        data.remove(id)
        write()
    }
}

