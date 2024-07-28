import tkinter as tk
from tkinter import ttk
import mysql.connector
from tkintertable import TableCanvas, TableModel
config = {
    'host':'proyectosbdgrupo8.mysql.database.azure.com',
    'user':'main',
    'password':'proyectoSBD=Grupo8_',
    'database':'wuanplus'
}
def ejecutar_consulta(query):
    """Ejecuta una consulta SQL y devuelve los resultados."""
    conn = mysql.connector.connect(**config)
    cursor = conn.cursor()
    cursor.execute(query)
    resultados = cursor.fetchall()
    columnas = [i[0] for i in cursor.description]
    conn.close()
    return columnas, resultados

def mostrar_resultados(query):
    """Muestra los resultados de la consulta en la tabla."""
    columnas, resultados = ejecutar_consulta(query)
    
    # Crear el modelo de tabla
    table_model = TableModel()
    table_model.importDict({
        'data': [dict(zip(columnas, fila)) for fila in resultados],
        'header': columnas
    })
    
    # Actualizar el modelo de la tabla y redibujar
    table.updateModel(table_model)
    table.redraw()

# Crear la ventana principal
root = tk.Tk()
root.title("Consulta a MySQL")

# Crear un marco para los botones
frame_botones = tk.Frame(root)
frame_botones.pack(pady=10)

# Botón 1
boton1 = tk.Button(frame_botones, text="Consulta 1", command=lambda: mostrar_resultados("SELECT * FROM empleado"))
boton1.pack(side=tk.LEFT, padx=5)

# Botón 2
boton2 = tk.Button(frame_botones, text="Consulta 2", command=lambda: mostrar_resultados("SELECT * FROM factura"))
boton2.pack(side=tk.LEFT, padx=5)

# Crear un marco para la tabla
frame_tabla = tk.Frame(root)
frame_tabla.pack(padx=10, pady=10)

# Crear la tabla
table = TableCanvas(frame_tabla, model=TableModel())
table.show()

# Ejecutar el bucle de eventos
root.mainloop()