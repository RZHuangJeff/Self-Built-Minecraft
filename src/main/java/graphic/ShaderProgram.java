package graphic;

import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;

public class ShaderProgram {
    private int programId;
    private int vShaderId;
    private int fShaderId;

    private HashMap<String, Integer> uniforms;
    
    public ShaderProgram() throws Exception{
        programId = glCreateProgram();
        if(programId == 0)
            throw new Exception("Unable to create shader program.");
        uniforms = new HashMap<>();   
    }

    public void createVertexShader(String code) throws Exception{
        vShaderId = createShader(GL_VERTEX_SHADER, code);
    }

    public void createFragmentShader(String code) throws Exception{
        fShaderId = createShader(GL_FRAGMENT_SHADER, code);
    }

    public void createUniform(String uniName) throws Exception{
        int uniLocation = glGetUniformLocation(programId, uniName);
        if(uniLocation < 0)
            throw new Exception("Could not find uniform.");
        uniforms.put(uniName, uniLocation);
    }

    public void setUniform(String name, Matrix4f matrix){
        try (MemoryStack stack = MemoryStack.stackPush()) {
            glUniformMatrix4fv(uniforms.get(name), false, 
                matrix.get(stack.mallocFloat(16)));
        }
    }

    public void setUniform(String name, int value){
            glUniform1i(uniforms.get(name), value);
    }

    public void setUniform(String name, Vector4f value){
        glUniform4f(uniforms.get(name), value.x, value.y, value.z, value.w);
}

    private int createShader(int type, String code) throws Exception{
        int shaderId = glCreateShader(type);
        if(shaderId == 0)
            throw new Exception("Unable to create shader.");

        glShaderSource(shaderId, code);
        glCompileShader(shaderId);
        if(glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0)
            throw new Exception("Unable to compile shader." + glGetShaderInfoLog(shaderId, 1024));

        glAttachShader(programId, shaderId);

        return shaderId;
    }

    public void link() throws Exception{
        glLinkProgram(programId);
        if(glGetProgrami(programId, GL_LINK_STATUS) == 0)
            throw new Exception("Unable to link program.");
        
        if(vShaderId != 0)
            glDetachShader(programId, vShaderId);
        if(fShaderId != 0)
            glDetachShader(programId, fShaderId);

        glValidateProgram(programId);
    }   

    public void bind(){
        glUseProgram(programId);
    }

    public void unbind(){
        glUseProgram(0);
    }

    public void cleanUp(){
        unbind();
        if(programId != 0)
            glDeleteProgram(programId);
    }
}